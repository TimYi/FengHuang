package com.fenghuangzhujia.eshop.materialManage.product.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ProductVo extends DtoBaseModel {

	/**产品名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
	
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
}
