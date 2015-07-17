package com.fenghuangzhujia.eshop.core.coupons.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CouponsDto extends DtoBaseModel {

	private UserDto user;
	private String name;
	private Double couponsMoney;
	private Date expireTime;
	private boolean expired;
	private boolean used;
	private boolean readed;
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCouponsMoney() {
		return couponsMoney;
	}
	public void setCouponsMoney(Double couponsMoney) {
		this.couponsMoney = couponsMoney;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
}
