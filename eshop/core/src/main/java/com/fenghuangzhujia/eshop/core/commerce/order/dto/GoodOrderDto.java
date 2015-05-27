package com.fenghuangzhujia.eshop.core.commerce.order.dto;

import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class GoodOrderDto extends DtoBaseModel {	
	private double price;	
	private OrderStatus status;			
	private Integer count;
	private String name;
	private String type;
	private String mainPic;
	private String shopid;
	private String shop;	
	private Object good;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMainPic() {
		return mainPic;
	}

	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public Object getGood() {
		return good;
	}

	public void setGood(Object good) {
		this.good = good;
	}
}
