package com.fenghuangzhujia.eshop.core.commerce.payment;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fenghuangzhujia.eshop.core.commerce.order.Order;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 订单支付记录
 * @author pc
 *
 */
//@Entity
public class OrderPayment extends UUIDBaseModel {
	private Order order;
	private Set<Payment> payments;
	
	/**
	 * 所属订单
	 * @return
	 */
	@OneToOne(mappedBy="payment")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * 具体支付信息
	 * @return
	 */
	@OneToMany(mappedBy="orderPayment")
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
}
