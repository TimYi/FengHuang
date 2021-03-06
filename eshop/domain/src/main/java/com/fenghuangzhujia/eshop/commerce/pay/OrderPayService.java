package com.fenghuangzhujia.eshop.commerce.pay;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.coupons.Coupons;
import com.fenghuangzhujia.eshop.core.couponsDef.CouponsAllocater;

@Service
@Transactional
public class OrderPayService {
	
	@Autowired
	private OrderPayRepository orderPayRepository;
	@Autowired
	private GoodOrderRepository orderRepository;
	@Autowired
	private CouponsAllocater couponsAllocater;

	/**
	 * 使用优惠券减免支付金额
	 * 计算优惠券优惠之后的订单金额
	 * 将优惠券核销
	 * 不同种优惠券可以同时使用，每种限用一张。
	 * 将订单应付金额和优惠金额改动并持久化
	 * 应该在一个持久化transaction内部执行此方法
	 */
	public void useCoupons(OrderPay orderPay, Set<Coupons> couponses) {
		//取消之前使用的优惠券
		if(orderPay.getCouponses()!=null) {
			for (Coupons coupons : orderPay.getCouponses()) {
				coupons.setUsed(false);				
			}
		}		
		
		//使用新优惠券
		Double couponsMoney=0.0;
		for (Coupons coupons : couponses) {
			coupons.setUsed(true);
			couponsMoney=couponsMoney+coupons.getCouponsMoney();
		}
		orderPay.setCouponses(couponses);
		orderPay.setCouponsMoney(couponsMoney);
		Double shouldPayMoney=orderPay.getTotalMoney()-couponsMoney;
		if(shouldPayMoney<0)shouldPayMoney=0.0;
		orderPay.setShouldPayMoney(shouldPayMoney);
	}
	
	/**
	 * 处理支付成功回调
	 * 判断支付金额是否匹配，并记录支付信息
	 * 如果匹配，修改支付状态，并修改订单状态
	 * @param pay 			确定处理哪个支付
	 * @param payedMoney	用户支付金额，只有支付金额匹配，才修改支付状态
	 */
	public void revoke(OrderPay pay, Double payedMoney) {
		pay.setPayedMoney(payedMoney);
		pay.setPayTime(new Date());
		if(payedMoney>=pay.getShouldPayMoney()) {
			pay.setHasPayed(true);
			GoodOrder order=pay.getOrder();
			if(order.getStatus()==OrderStatus.WAITING) {
				order.setStatus(OrderStatus.PAYED);
				orderRepository.save(order);
			}			
		}
		orderPayRepository.save(pay);
		
		//触发支付成功分发优惠券事件
		couponsAllocater.allocate(CouponsAllocater.PAY_ORDER, pay.getOrder().getUser().getId());
		
	}
}
