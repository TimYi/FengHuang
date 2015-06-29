package com.fenghuangzhujia.eshop.core.commerce.pay;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.PayType;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.pay.KhzfResponseData;
import org.sharechina.pay.pufa.service.KhzfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.commerce.coupons.Coupons;
import com.fenghuangzhujia.eshop.core.commerce.coupons.CouponsRepository;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class PufaPayService {
	
	/**客户号*/
	private static final String MERC_CODE="983708160009501";
	
	private static Logger logger=LoggerFactory.getLogger(PufaPayService.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GoodOrderRepository orderRepository;
	@Autowired
	private CouponsRepository couponsRepository;
	@Autowired
	private PufaPayRepository pufaPayRepository;
	@Autowired
	private OrderPayService orderPayService;
	@Autowired
	private OrderPayRepository orderPayRepository;
	
	
	
	/**
	 * 计算优惠券优惠金额
	 * 核销优惠券
	 * 计算实际应付金额
	 * 计算并返回浦发支付所需参数
	 * @param userId
	 * @param orderId
	 * @param couponsIds
	 * @param payBank
	 * @param accountTypeused
	 * @return
	 */
	public RequestModel calculatePayArgs(String userId, String orderId,
			String[] couponsIds, PayBank payBank, AccountType accountType) {
		User user=userRepository.findOne(userId);
		if(user==null) throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "错误的用户");
		GoodOrder order=orderRepository.findOne(orderId);
		if(order==null)throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "空订单错误");
		if(!order.getUser().getId().equals(userId))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的订单");
		
		OrderPay pay=order.getPayment();
		if(pay.getHasPayed())
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "订单已经支付成功，请勿重复支付");
		
		//计算优惠结果		
		if(couponsIds!=null) {	
			Set<Coupons> couponsSet=new HashSet<Coupons>();
			for (String couponsId : couponsIds) {
				Coupons coupons=couponsRepository.findOne(couponsId);
				if(!coupons.getUser().getId().equals(userId))
					throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的优惠券");
				if(coupons.isUsed())
					throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "优惠券已使用");
				couponsSet.add(coupons);		
			}
			orderPayService.useCoupons(pay, couponsSet);//将优惠计算委托给上级服务
		}				
		
		//生成12位订单号的浦发支付，并和OrderPay关联
		if(pay.getPufaPay()==null) {//第一次请求支付
			PufaPay pufaPay=new PufaPay();
			String termSsn=CodeGenerater.generateOrderCode();
			pufaPay.setTermSsn(termSsn);
			pay.setPufaPay(pufaPay);
		}		
		
		//临时使用此回调地址
		//TODO 修改成可配置内容
		URL revokeUrl;
		try {
			revokeUrl=new URL("http://101.200.229.135/pufa/revoke");
		} catch (MalformedURLException e) {
			revokeUrl=null;
		}
		//调用浦发支付服务，计算支付参数并返回
		return KhzfService.getKhzfRequestData(null, pay.getPufaPay().getTermSsn(), MERC_CODE, null, 
				pay.getShouldPayMoney(), payBank, accountType, PayType.BUY, order.getName(), order.getName(),
				null, null, revokeUrl, null);
	}
	
	/**
	 * 处理支付结果回调
	 * 判断支付结果并修改支付状态
	 * 如果支付成功，修改浦发支付为已支付，并记录相关参数
	 * 之后，通知OrderPay修改支付状态
	 * 由OrderPayService负责通知Order修改相应状态
	 * @param xml
	 */
	public void revoke(String xml) {
		try {
			ResponseModel<KhzfResponseData> response=KhzfService.resolveKhzfResult(xml);
			if(!response.isSuccess()) {
				//TODO 今后可能会做一些额外记录
				return;
			}
			KhzfResponseData data=response.getData();
			if(!data.getRespCode().equals("00")) {
				//TODO 一些额外记录
				return;
			}
			//以下表示支付成功
			PufaPay pufaPay=pufaPayRepository.getByTermSsn(data.getTermSsn());
			pufaPay.setAcqSsn(data.getAcqSsn());
			pufaPay.setClearDate(data.getClearDate());
			pufaPay.setHasPayed(true);
			pufaPay.setPayTime(new Date());
			pufaPay.setTranAmt(data.getTranAmt());
			
			//调用OrderPayService回调
			OrderPay pay=orderPayRepository.getByPufaPay(pufaPay);
			orderPayService.revoke(pay, pufaPay.getTranAmt());			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}		
	}
}
