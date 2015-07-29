package com.fenghuangzhujia.eshop.commerce.pay;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.PayType;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.pay.KhzfResponseData;
import org.sharechina.pay.pufa.protocal.refund.RefundResponseData;
import org.sharechina.pay.pufa.service.KhthService;
import org.sharechina.pay.pufa.service.KhzfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.SourceType;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.coupons.Coupons;
import com.fenghuangzhujia.eshop.core.coupons.CouponsRepository;
import com.fenghuangzhujia.eshop.core.rlmessage.MessageSender;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppointService;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.scramble.PackageGood;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class PufaPayService {
	
	/**客户号*/
	//private static final String MERC_CODE="983708160009501";//测试客户号
	private static final String MERC_CODE="360448160000101";//正式客户号
	
	//private static Logger logger=LoggerFactory.getLogger(PufaPayService.class);

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
	@Autowired
	private MessageSender messageSender;
	@Autowired
	private PackageAppointService packageAppointService;
	@Autowired
	private KhthService khthService;	
	
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
			String couponsId, PayBank payBank, AccountType accountType, SourceType source) {
		User user=userRepository.findOne(userId);
		if(user==null) throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "错误的用户");
		GoodOrder order=orderRepository.findOne(orderId);
		if(order==null)throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "空订单错误");
		if(!order.getUser().getId().equals(userId))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的订单");
		
		//设置订单来源
		order.setSource(source);
		
		OrderPay pay=order.getPayment();
		if(pay==null) {
			pay=new OrderPay();
			pay.setTotalMoney(order.getPrice());
			pay.setShouldPayMoney(order.getPrice());
			orderPayRepository.save(pay);
			order.setPayment(pay);			
		}
		if(pay.getHasPayed())
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "订单已经支付成功，请勿重复支付");
		
		//计算优惠结果		
		if(StringUtils.isNotBlank(couponsId)) {	
			Coupons coupons=couponsRepository.findOne(couponsId);
			if(coupons!=null) {
				Set<Coupons> couponsSet=new HashSet<Coupons>();
				couponsSet.add(coupons);
				orderPayService.useCoupons(pay, couponsSet);//将优惠计算委托给上级服务
			}			
		}				
		
		//生成12位订单号的浦发支付，并和OrderPay关联
		String termSsn=CodeGenerater.generateOrderCode();//如果不重新生成，会造成重复交易错误。
		if(pay.getPufaPay()==null) {//第一次请求支付
			PufaPay pufaPay=new PufaPay();			
			pufaPayRepository.save(pufaPay);
			pay.setPufaPay(pufaPay);	
		}		
		pay.getPufaPay().setTermSsn(termSsn);//确保交易识别号变更
		
		//临时使用此回调地址
		//TODO 修改成可配置内容
		URL revokeUrl;
		try {
			revokeUrl=new URL("http://IFHZJ.com/api/pufa/revoke");
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
	 * @param plain 参数Plain域
	 * @param signature 签名Signature字段
	 * @return 支付id
	 */
	public PufaPay revoke(String plain, String signature) {
		try {
			KhzfResponseData response=KhzfService.resolveKhzfResult(plain, signature);
			if(!response.getRespCode().equals("00")) {
				throw new ErrorCodeException(SystemErrorCodes.PAY_FAILED, response.getRespCode());
			}
			//以下表示支付成功
			PufaPay pufaPay=pufaPayRepository.getByTermSsn(response.getTermSsn());
			pufaPay.setAcqSsn(response.getAcqSsn());
			pufaPay.setClearDate(response.getClearDate());
			pufaPay.setHasPayed(true);
			pufaPay.setPayTime(new Date());
			pufaPay.setTranAmt(response.getTranAmt());
			pufaPayRepository.save(pufaPay);
			
			//调用OrderPayService回调
			OrderPay pay=orderPayRepository.getByPufaPay(pufaPay);
			orderPayService.revoke(pay, pufaPay.getTranAmt());	
			
			//发送支付成功通知短信
			try {
				PackageGood pGood=((PackageGood)pay.getOrder().getGood());
				DecoratePackage dPackage=pGood.getDecoratePackage();
				messageSender.paySuccess(pay.getOrder().getMobile(), pay.getOrder().getUser().getRealName(),
						pay.getPayTime(), dPackage.getName(),
						pay.getPayedMoney(), pay.getOrder().getCode());
			} catch (Exception e) {
				LogUtils.errorLog(e);
				//无论短信通知结果如何，不要影响正常流程进行
			}
			
			//重置用户预约为可用
			try {
				String userId=pay.getOrder().getUser().getId();
				//TODO 优化这里的设计
				String packageId=((PackageGood)(pay.getOrder().getGood())).getDecoratePackage().getId();
				packageAppointService.resetAppoint(userId, packageId);
			} catch (Exception e) {
				LogUtils.errorLog(e);
			}
			
			return pufaPay;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
	/**
	 * 如果退款成功，直接改变支付状态。如果退款失败，抛出异常。
	 * @param pay
	 * @throws ErrorCodeException
	 */
	public void drawback(PufaPay pay) throws ErrorCodeException {
		try {
			String drawbackTermSsn=CodeGenerater.generateOrderCode();
			ResponseModel<RefundResponseData> result=khthService.sendKhthRequest(null, drawbackTermSsn, 
					pay.getClearDate(), pay.getAcqSsn(), MERC_CODE, null, pay.getTranAmt(), null, null);
			if(!result.isSuccess()) {
				throw new ErrorCodeException(SystemErrorCodes.DRAWBACK_FAILED, result.getErrorMsg());
			}			
			RefundResponseData data=result.getData();
			if(!data.getRespCode().equals("00")) {
				throw new ErrorCodeException(SystemErrorCodes.DRAWBACK_FAILED, "错误码："+data.getRespCode());
			}
			
			pay.setDrawbacked(true);
			pay.setDrawbackTermSsn(drawbackTermSsn);
		} catch (SignatureException e) {
			throw new ErrorCodeException(SystemErrorCodes.PAY_FAILED, "签名错误");
		}
	}
	
	/**
	 * 根据浦发支付id，获取订单id
	 * @param id
	 * @return
	 */
	public String findOrderByPufaPay(String id) {
		PufaPay pufaPay=pufaPayRepository.findOne(id);
		OrderPay pay=orderPayRepository.getByPufaPay(pufaPay);
		return pay.getOrder().getId();
	}
}
