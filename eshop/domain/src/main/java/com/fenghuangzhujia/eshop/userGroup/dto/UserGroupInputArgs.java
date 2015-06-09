package com.fenghuangzhujia.eshop.userGroup.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class UserGroupInputArgs extends DtoBaseModel {

	private String name;
	private long expVal;
	private int stars;
	private String colorid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getExpVal() {
		return expVal;
	}
	public void setExpVal(long expVal) {
		this.expVal = expVal;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getColorid() {
		return colorid;
	}
	public void setColorid(String colorid) {
		this.colorid = colorid;
	}
}
