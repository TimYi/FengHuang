package com.fenghuangzhujia.eshop.core.brand.dto;

import java.util.Set;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class BrandTypeDto extends DtoBaseModel {
	private String name;
	private Set<BrandTypeDto> subTypes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<BrandTypeDto> getSubTypes() {
		return subTypes;
	}
	public void setSubTypes(Set<BrandTypeDto> subTypes) {
		this.subTypes = subTypes;
	}
}
