package com.fenghuangzhujia.eshop.core.commerce.eshop;

import javax.persistence.Entity;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 店铺类型，自营还是其它等
 * 暂时还没确定如何设计
 * @author pc
 *
 */
//@Entity
public class ShopType extends UUIDBaseModel {
	
	private String type;
	private String description;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
