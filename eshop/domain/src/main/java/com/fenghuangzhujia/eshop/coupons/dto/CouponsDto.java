package com.fenghuangzhujia.eshop.coupons.dto;

import java.util.Date;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CouponsDto extends DtoBaseModel {

	private String userid;
	private String name;
	private String defid;
	private CouponsConstrainDto constrain;
	private String constrainType;
	private String constrainName;
	private CouponsStragyDto stragy;
	private String stragyType;
	private String stragyName;
	private Double couponsMoney;
	private Date expireTime;
	private boolean expired;
	private boolean used;
	private boolean readed;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefid() {
		return defid;
	}
	public void setDefid(String defid) {
		this.defid = defid;
	}
	public CouponsConstrainDto getConstrain() {
		return constrain;
	}
	public void setConstrain(CouponsConstrainDto constrain) {
		this.constrain = constrain;
	}
	public String getConstrainType() {
		return constrainType;
	}
	public void setConstrainType(String constrainType) {
		this.constrainType = constrainType;
	}
	public String getConstrainName() {
		return constrainName;
	}
	public void setConstrainName(String constrainName) {
		this.constrainName = constrainName;
	}
	public CouponsStragyDto getStragy() {
		return stragy;
	}
	public void setStragy(CouponsStragyDto stragy) {
		this.stragy = stragy;
	}
	public String getStragyType() {
		return stragyType;
	}
	public void setStragyType(String stragyType) {
		this.stragyType = stragyType;
	}
	public String getStragyName() {
		return stragyName;
	}
	public void setStragyName(String stragyName) {
		this.stragyName = stragyName;
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
