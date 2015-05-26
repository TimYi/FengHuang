package com.fenghuangzhujia.eshop.core.commerce.order;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 商品订单
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_good_order")
public class GoodOrder extends UUIDBaseModel {
	
	private User user;
	
	private double price;
	
	private OrderStatus status;
	
	private Good good;
	
	private Integer count;
	
	/**
	 * 商品名称
	 * @return
	 */
	@Transient
	public String getName() {
		return good.getName();
	}
	
	/**
	 * 商品类型
	 * @return
	 */
	@Transient
	public String getType() {
		return good.getType();
	}
	
	/**
	 * 商品主图
	 * @return
	 */
	@Transient
	public String getMainPic() {
		return good.getMainPic();
	}
	
	/**
	 * 商品卖家
	 * @return
	 */
	@Transient
	public Shop getShop() {
		return good.getShop();
	}
	
	/**
	 * 商品买家
	 * @return
	 */
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
