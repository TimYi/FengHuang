package com.fenghuangzhujia.eshop.meteriaManage.product.dto;

import com.fenghuangzhujia.eshop.meteriaManage.brand.dto.SimpleBrandDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ProductVo extends DtoBaseModel {

	/**产品名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
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
	public SimpleBrandDto getBrand() {
		return brand;
	}
	public void setBrand(SimpleBrandDto brand) {
		this.brand = brand;
	}
}
