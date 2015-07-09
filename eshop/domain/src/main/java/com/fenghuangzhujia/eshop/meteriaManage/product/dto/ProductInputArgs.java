package com.fenghuangzhujia.eshop.meteriaManage.product.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ProductInputArgs extends DtoBaseModel {

	/**产品名称*/
	private String name;
	/**品牌*/
	private String brandId;
	/**排序序号*/
	private int ordernum;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
}
