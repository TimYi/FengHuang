package com.fenghuangzhujia.eshop.prudoct.packageContent.meteria.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MaterialInputArgs extends DtoBaseModel {

	/**所属套餐内容*/
	private String contentId;
	/**排序序号*/
	private int ordernum;
	/**使用材料*/
	private String materialId;
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
}
