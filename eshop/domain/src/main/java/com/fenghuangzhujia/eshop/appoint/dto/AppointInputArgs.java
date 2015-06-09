package com.fenghuangzhujia.eshop.appoint.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class AppointInputArgs extends DtoBaseModel {

	private String userid;
	private String typeid;
	private String address;
	private String mobile;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date appointTime;
	private String areaid;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
}
