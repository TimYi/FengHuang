package com.fenghuangzhujia.eshop.core.menu.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MenuVo extends DtoBaseModel {

	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
