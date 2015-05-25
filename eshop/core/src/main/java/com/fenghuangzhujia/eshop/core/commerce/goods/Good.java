package com.fenghuangzhujia.eshop.core.commerce.goods;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 商品
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_good")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="discrininator")
public abstract class Good extends UUIDBaseModel {
	private String name;
	private double price;
	private double realPrice;
	private Shop shop;
	
	/**
	 * 商品名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品吊盘价格
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * 商品实际销售价格
	 * @return
	 */
	public double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
	
	/**
	 * 商品必然从属于某个店铺所有
	 * @return
	 */
	@ManyToOne(optional=false)
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	/**
	 * 商品主图
	 * @return
	 */
	@Transient
	public abstract String getMainPic();
}
