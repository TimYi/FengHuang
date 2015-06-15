package com.fenghuangzhujia.eshop.appoint.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

public class AppointDto extends DtoBaseModel {

	private UserDto user;
	private CategoryItemDto type;
	private AreaDto area;
	private String mobile;
	private boolean readed;
	private String realName;
	private Date createTime;
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public CategoryItemDto getType() {
		return type;
	}
	public void setType(CategoryItemDto type) {
		this.type = type;
	}	
	public AreaDto getArea() {
		return area;
	}
	public void setArea(AreaDto area) {
		this.area = area;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
