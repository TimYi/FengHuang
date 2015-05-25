package com.fenghuangzhujia.eshop.core.commerce.service;

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
 * 服务，服务的特点是在实际消费时才能确定商品细节。
 * 服务在每次消费确定细节之后，生成一个服务商品。
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_service")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="discriminator")
public abstract class Service extends UUIDBaseModel {
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
	 * 服务价格，计量单位依子类而定
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if(price<0)return;
		this.price = price;
	}
	
	/**
	 * 服务实际价格
	 * @return
	 */
	public double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
	
	/**
	 * 服务也需要从属于某个店铺
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
