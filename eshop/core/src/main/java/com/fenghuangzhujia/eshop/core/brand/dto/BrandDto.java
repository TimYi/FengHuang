package com.fenghuangzhujia.eshop.core.brand.dto;

import java.util.Set;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class BrandDto extends DtoBaseModel {
	private String name;
	private Set<BrandDto> subBrands;
	private BrandTypeDto brandType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<BrandDto> getSubBrands() {
		return subBrands;
	}
	public void setSubBrands(Set<BrandDto> subBrands) {
		this.subBrands = subBrands;
	}
	public BrandTypeDto getBrandType() {
		return brandType;
	}
	public void setBrandType(BrandTypeDto brandType) {
		this.brandType = brandType;
	}
}
