package com.fenghuangzhujia.eshop.prudoct.packageContent.meteria.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MaterialDto extends DtoBaseModel {

	/**排序序号*/
	private int ordernum;
	/**使用材料*/
	private com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialDto material;
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(
			com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialDto material) {
		this.material = material;
	}
}
