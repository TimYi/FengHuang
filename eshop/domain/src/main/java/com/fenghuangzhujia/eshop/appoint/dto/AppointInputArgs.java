package com.fenghuangzhujia.eshop.appoint.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class AppointInputArgs extends DtoBaseModel {

	private String userid;
	private String typeid;
	private String mobile;
	//用于短信预约验证
	private String validater;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
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
