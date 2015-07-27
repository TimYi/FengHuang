package com.fenghuangzhujia.eshop.view.decorateTechnology.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class DecorateTechnologyInputArgs extends DtoBaseModel {

	/**工艺名称*/
	private String name;
	/**序号*/
	private int ordernum;
	/**详情*/
	private String description;
	/**工艺图*/
	private MultipartFile picFile;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getPicFile() {
		return picFile;
	}
	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
	}
}
