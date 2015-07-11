package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class DecoratePackageVo extends DtoBaseModel {

	/**套餐价格(元/m2)*/
	private Double price;
	/**套餐名称*/
	private String name;
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
