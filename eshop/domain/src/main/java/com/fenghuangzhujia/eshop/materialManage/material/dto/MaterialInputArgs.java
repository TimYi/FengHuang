package com.fenghuangzhujia.eshop.materialManage.material.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MaterialInputArgs extends DtoBaseModel {

	/**主材所属产品*/
	private String productId;
	/**排序序号*/
	private int ordernum;
	/**展示图片*/
	private MultipartFile picFile;
	/**对主材的描述信息*/
	private String description;
	/**对应家具，灯具，家具饰品等品类*/
	private String typeName;
	/**使用此材料的套餐id*/
	private String[] packageIds;
	
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String[] getPackageIds() {
		return packageIds;
	}
	public void setPackageIds(String[] packageIds) {
		this.packageIds = packageIds;
	}
}
