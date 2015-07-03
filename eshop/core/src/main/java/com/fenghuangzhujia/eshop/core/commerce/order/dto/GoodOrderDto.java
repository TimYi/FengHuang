package com.fenghuangzhujia.eshop.core.commerce.order.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class GoodOrderDto extends DtoBaseModel {	
	private double price;	
	private OrderStatus status;			
	private Integer count;
	private String name;
	private String type;
	private MediaContentDto mainPic;
	private String shopid;
	private String shop;	
	private Object good;
	/**交易时间*/
	private Date payTime;
	private String code;

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
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
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
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
