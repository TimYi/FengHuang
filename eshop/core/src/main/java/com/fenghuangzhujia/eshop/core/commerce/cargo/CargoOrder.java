package com.fenghuangzhujia.eshop.core.commerce.cargo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 购物车订单
 * @author pc
 *
 */
@Entity
public class CargoOrder extends UUIDBaseModel {
	
	private User user;
	
	private Good good;
	
	private Integer count;
	
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Transient
	public Shop getShop() {
		return getGood().getShop();
	}
	
	@Transient
	public String getName() {
		return getGood().getName();
	}
	
	@Transient
	public String getMainPic() {
		return getGood().getMainPic();
	}
	
	@Transient
	public Double getPrice() {
		return getGood().getPrice();
	}
	
	@Transient
	public Double getRealPrice() {
		return getGood().getRealPrice();
	}
}
