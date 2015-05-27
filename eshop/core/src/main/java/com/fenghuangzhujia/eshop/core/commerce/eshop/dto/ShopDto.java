package com.fenghuangzhujia.eshop.core.commerce.eshop.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ShopDto extends DtoBaseModel {
	private String name;
	private String title;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
