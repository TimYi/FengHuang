package com.fenghuangzhujia.eshop.core.commerce.pay;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.commerce.coupons.Coupons;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;

@Service
@Transactional
public class OrderPayService {
	
	@Autowired
	private OrderPayRepository orderPayRepository;
	@Autowired
	private GoodOrderRepository orderRepository;

	/**
	 * 使用优惠券减免支付金额
	 * 计算优惠券优惠之后的订单金额
	 * 将优惠券核销
	 * 将订单应付金额和优惠金额改动并持久化
	 * 应该在一个持久化transaction内部执行此方法
	 */
	public void useCoupons(OrderPay orderPay, Set<Coupons> couponses) {
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
	}
}
