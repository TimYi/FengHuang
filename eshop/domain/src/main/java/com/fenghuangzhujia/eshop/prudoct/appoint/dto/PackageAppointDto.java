package com.fenghuangzhujia.eshop.prudoct.appoint.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class PackageAppointDto extends DtoBaseModel {

	private UserDto user;
	private AreaDto city;
	private DecoratePackageDto decoratePackage;
	private String realName;
	private String mobile;
	private Date createTime;
	private Date expireTime;
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public AreaDto getCity() {
		return city;
	}
	public void setCity(AreaDto city) {
		this.city = city;
	}
	public DecoratePackageDto getDecoratePackage() {
		return decoratePackage;
	}
	public void setDecoratePackage(DecoratePackageDto decoratePackage) {
		this.decoratePackage = decoratePackage;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
