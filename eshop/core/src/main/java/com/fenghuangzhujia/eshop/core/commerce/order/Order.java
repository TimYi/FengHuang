package com.fenghuangzhujia.eshop.core.commerce.order;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.eshop.core.commerce.payment.OrderPayment;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 订单
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_order_header")
public class Order extends UUIDBaseModel {
	private User buyer;
	private Shop solder;
	private Set<GoodOrder> goodOrders;
	//private OrderPayment payment;
	
	/**
     * 返回购买者
     * @return
     */
	@ManyToOne
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	/**
	 * 卖出商品的店铺
	 * @return
	 */
	@ManyToOne(optional=false)
	public Shop getSolder() {
		return solder;
	}
	public void setSolder(Shop solder) {
		this.solder = solder;
	}
	
	/**
	 * 实际卖出的商品，同一个店铺的商品可以统一下单
	 * @return
	 */
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	public Set<GoodOrder> getGoodOrders() {
		return goodOrders;
	}
	public void setGoodOrders(Set<GoodOrder> goodOrders) {
		this.goodOrders = goodOrders;
	}
	
	
	/*
	 * 订单支付信息
	 * @return
	 */
	/*
	@OneToOne(cascade=CascadeType.ALL)
	public OrderPayment getPayment() {
		return payment;
	}
	public void setPayment(OrderPayment payment) {
		this.payment = payment;
	}*/
}
