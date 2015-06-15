package com.fenghuangzhujia.eshop.appoint.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class AppointInputArgs extends DtoBaseModel {

	private String userId;
	private String typeId;
	private String cityId;
	private String realName;
	private String mobile;
	//用于短信预约验证
	private String validater;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userid) {
		this.userId = userid;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeid) {
		this.typeId = typeid;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getValidater() {
		return validater;
	}
	public void setValidater(String validater) {
		this.validater = validater;
	}
}
