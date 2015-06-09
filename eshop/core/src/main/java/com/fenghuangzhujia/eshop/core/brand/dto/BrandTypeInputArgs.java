package com.fenghuangzhujia.eshop.core.brand.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class BrandTypeInputArgs extends DtoBaseModel {

	private String name;
	private String superTypeId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuperTypeId() {
		return superTypeId;
	}
	public void setSuperTypeId(String superTypeId) {
		this.superTypeId = superTypeId;
	}
}
