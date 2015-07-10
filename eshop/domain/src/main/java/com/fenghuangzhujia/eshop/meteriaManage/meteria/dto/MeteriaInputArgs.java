package com.fenghuangzhujia.eshop.meteriaManage.meteria.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MeteriaInputArgs extends DtoBaseModel {

	/**主材所属产品*/
	private String productId;
	/**排序序号*/
	private int ordernum;
	/**展示图片*/
	private MultipartFile picFile;
	/**对主材的描述信息*/
	private String description;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public MultipartFile getPicFile() {
		return picFile;
	}
	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
