package com.fenghuangzhujia.eshop.prudoct.packages.space.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class DecorateItemDto extends DtoBaseModel {

	/**项目名称*/
	private String name;
	/**序号*/
	private int ordernum;
	/**品牌*/
	private String brand;
	/**数量*/
	private String number;
	/**详细说明*/
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
