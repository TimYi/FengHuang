package com.fenghuangzhujia.eshop.core.commerce.order;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 商品订单
 * @author pc
 *
 */
@Entity
public class GoodOrder extends UUIDBaseModel {
	
	private double price;
	
	private Good good;
	
	private Integer count;
	
	private Order order;

	/**
	 * 商品购买时单价
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 商品
	 * @return
	 */
	@ManyToOne(optional=false)
	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public Integer getCount() {
		return count;
	}

	/**
	 * 购买总数
	 * @return
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/**
	 * 获取从属的订单
	 * @return
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
