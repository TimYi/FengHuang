package com.fenghuangzhujia.eshop.prudoct.appoint.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class PackageAppointInputArgs extends DtoBaseModel {

	private String userId;
	private String cityId;
	private String decoratePackageId;
	private String realName;
	private String mobile;
	private String validater;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDecoratePackageId() {
		return decoratePackageId;
	}
	public void setDecoratePackageId(String decoratePackageId) {
		this.decoratePackageId = decoratePackageId;
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
