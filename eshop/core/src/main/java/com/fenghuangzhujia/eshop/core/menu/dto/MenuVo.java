package com.fenghuangzhujia.eshop.core.menu.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MenuVo extends DtoBaseModel {

	private String code;
	private String name;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
