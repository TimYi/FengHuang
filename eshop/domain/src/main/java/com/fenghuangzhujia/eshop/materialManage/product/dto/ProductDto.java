package com.fenghuangzhujia.eshop.materialManage.product.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.materialManage.brand.dto.SimpleBrandDto;
import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ProductDto extends DtoBaseModel {

	/**产品名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
	/**品牌主材展示*/
	private Set<MaterialDto> materials;
	/**品牌*/
	private SimpleBrandDto brand;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public Set<MaterialDto> getMaterials() {
		return materials;
	}
	public void setMaterials(Set<MaterialDto> materials) {
		this.materials = materials;
	}
	public SimpleBrandDto getBrand() {
		return brand;
	}
	public void setBrand(SimpleBrandDto brand) {
		this.brand = brand;
	}
}
