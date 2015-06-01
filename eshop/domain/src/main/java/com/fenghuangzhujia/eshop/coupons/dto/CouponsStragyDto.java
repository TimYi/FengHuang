package com.fenghuangzhujia.eshop.coupons.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CouponsStragyDto extends DtoBaseModel {

	private String type;
	private String name;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
