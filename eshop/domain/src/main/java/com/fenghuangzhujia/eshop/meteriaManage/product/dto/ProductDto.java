package com.fenghuangzhujia.eshop.meteriaManage.product.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.meteriaManage.meteria.dto.MeteriaDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ProductDto extends DtoBaseModel {

	/**产品名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
	/**品牌主材展示*/
	private Set<MeteriaDto> meterias;
	
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
	public Set<MeteriaDto> getMeterias() {
		return meterias;
	}
	public void setMeterias(Set<MeteriaDto> meterias) {
		this.meterias = meterias;
	}
}
