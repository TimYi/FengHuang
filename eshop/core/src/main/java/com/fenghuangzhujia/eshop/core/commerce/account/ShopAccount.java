package com.fenghuangzhujia.eshop.core.commerce.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;

/**
 * 店铺账户
 * @author pc
 *
 */
//@Entity
@DiscriminatorValue(value="SHOP")
public class ShopAccount extends Account {
	private Shop shop;

	@ManyToOne(optional=false)
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}	
}
