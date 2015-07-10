package com.fenghuangzhujia.eshop.materialManage.brand.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class BrandInputArgs extends DtoBaseModel {

	/**品牌名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
	/**品牌logo*/
	private MultipartFile logoFile;
	
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
	public MultipartFile getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}
}
