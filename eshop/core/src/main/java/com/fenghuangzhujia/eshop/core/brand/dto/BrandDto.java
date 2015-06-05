package com.fenghuangzhujia.eshop.core.brand.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class BrandDto extends DtoBaseModel {
	private String name;
	private Set<BrandDto> subBrands;
	private String brandType;
	private String brandTypeId;
	@JsonIgnore
	private String superBrandId;
	
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
	public String getBrandType() {
		return brandType;
	}
	public void setBrandType(String brandType) {
		this.brandType = brandType;
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
