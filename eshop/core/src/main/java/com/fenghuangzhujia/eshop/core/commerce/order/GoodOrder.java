package com.fenghuangzhujia.eshop.core.commerce.order;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 商品订单
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_good_order")
public class GoodOrder extends UUIDBaseModel {
	
	private double price;
	
	private OrderStatus status;
	
	private Good good;
	
	private Integer count;
	
	@Transient
	public String getName() {
		return good.getName();
	}
	
	@Transient
	public String getMainPic() {
		return good.getMainPic();
	}

	/**
	 * 订单状态
	 * @return
	 */
	@Enumerated
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

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
	
	public static enum OrderStatus {
		/**
		 * 未确认
		 */
		UNCONFIRM,
		/**
		 * 进行中
		 */
		PROCESSING,
		/**
		 * 已取消
		 */
		CANCEL,
		/**
		 * 已完成
		 */
		COMPLETE;
	}
}
