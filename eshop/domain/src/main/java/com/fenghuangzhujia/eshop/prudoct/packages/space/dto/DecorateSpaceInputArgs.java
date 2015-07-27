package com.fenghuangzhujia.eshop.prudoct.packages.space.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class DecorateSpaceInputArgs extends DtoBaseModel {

	/**所属套餐*/
	private String decoratePackageId;
	/**名称*/
	private String name;
	/**序号*/
	private int ordernum;
	/**第一张图片*/
	private MultipartFile picFile1;
	/**第二张图片*/
	private MultipartFile picFile2;
	/**第三张图片*/
	private MultipartFile picFile3;
	/**装修内容*/
	private List<DecorateItemDto> items;
	
	public String getDecoratePackageId() {
		return decoratePackageId;
	}
	public void setDecoratePackageId(String decoratePackageId) {
		this.decoratePackageId = decoratePackageId;
	}
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
	public MultipartFile getPicFile1() {
		return picFile1;
	}
	public void setPicFile1(MultipartFile picFile1) {
		this.picFile1 = picFile1;
	}
	public MultipartFile getPicFile2() {
		return picFile2;
	}
	public void setPicFile2(MultipartFile picFile2) {
		this.picFile2 = picFile2;
	}
	public MultipartFile getPicFile3() {
		return picFile3;
	}
	public void setPicFile3(MultipartFile picFile3) {
		this.picFile3 = picFile3;
	}
	public List<DecorateItemDto> getItems() {
		return items;
	}
	public void setItems(List<DecorateItemDto> items) {
		this.items = items;
	}
}
