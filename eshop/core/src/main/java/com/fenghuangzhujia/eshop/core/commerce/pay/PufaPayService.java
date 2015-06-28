package com.fenghuangzhujia.eshop.core.commerce.pay;

import java.util.HashSet;
import java.util.Set;

import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.PayType;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.service.KhzfService;
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

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GoodOrderRepository orderRepository;
	@Autowired
	private CouponsRepository couponsRepository;
	
	
	
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
			Set<String> couponsIds, PayBank payBank, AccountType accountType) {
		User user=userRepository.findOne(userId);
		if(user==null) throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "错误的用户");
		GoodOrder order=orderRepository.findOne(orderId);
		if(order==null)throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "空订单错误");
		
		//计算优惠结果
		OrderPay pay=order.getPayment();
		Double couponsMoney=0.0;
		if(couponsIds!=null) {			
			Set<Coupons> couponsSet=new HashSet<Coupons>();
			for (String couponsId : couponsIds) {
				Coupons coupons=couponsRepository.findOne(couponsId);
				if(!coupons.getUser().getId().equals(userId))
					throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的优惠券");
				coupons.setUsed(true);
				couponsSet.add(coupons);
				couponsMoney+=couponsMoney;				
			}
			pay.setCouponses(couponsSet);
		}		
		pay.setCouponsMoney(couponsMoney);
		Double shouldPayMoney=pay.getTotalMoney()-couponsMoney;
		if(shouldPayMoney<0)shouldPayMoney=0.0;
		pay.setShouldPayMoney(shouldPayMoney);
		
		PufaPay pufaPay=new PufaPay();
		String termSsn=CodeGenerater.generateOrderCode();
		pufaPay.setTermSsn(termSsn);
		pay.setPufaPay(pufaPay);
		return KhzfService.getKhzfRequestData(null, termSsn, MERC_CODE, null, pay.getShouldPayMoney(), 
				payBank, accountType, PayType.BUY, null, null, null, null, null, null);
	}
}
