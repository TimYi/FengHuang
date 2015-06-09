package com.fenghuangzhujia.eshop.core.brand.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class BrandInputArgs extends DtoBaseModel {

	private String name;
	private String brandTypeId;
	private String superBrandId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandTypeId() {
		return brandTypeId;
	}
	public void setBrandTypeId(String brandTypeId) {
		this.brandTypeId = brandTypeId;
	}
	public String getSuperBrandId() {
		return superBrandId;
	}
	public void setSuperBrandId(String superBrandId) {
		this.superBrandId = superBrandId;
	}
}
