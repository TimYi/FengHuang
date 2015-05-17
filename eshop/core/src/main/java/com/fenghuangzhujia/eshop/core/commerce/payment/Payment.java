package com.fenghuangzhujia.eshop.core.commerce.payment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.commerce.account.ShopAccount;
import com.fenghuangzhujia.eshop.core.commerce.account.UserAccount;
import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 一次具体的支付行为
 * @author pc
 *
 */
//@Entity
public class Payment extends UUIDBaseModel {
	private PaymentType type;
	private UserAccount fromAccount;
	private ShopAccount toAccount;
	private OrderPayment orderPayment;
	
	/**
	 * 支付类型
	 * @return
	 */
	@ManyToOne
	public PaymentType getType() {
		return type;
	}
	public void setType(PaymentType type) {
		this.type = type;
	}
	
	/**
	 * 付款账户
	 * @return
	 */
	@ManyToOne
	public UserAccount getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(UserAccount from) {
		this.fromAccount = from;
	}
	
	/**
	 * 收款账户
	 * @return
	 */
	@ManyToOne
	public ShopAccount getToAccount() {
		return toAccount;
	}
	public void setToAccount(ShopAccount to) {
		this.toAccount = to;
	}
	
	@Transient
	public Shop getShop() {
		return toAccount.getShop();
	}
	
	/**
	 * 一笔支付可能是一个 @OrderPayment 的子项
	 * @return
	 */
	@ManyToOne
	public OrderPayment getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(OrderPayment orderPayment) {
		this.orderPayment = orderPayment;
	}
}
