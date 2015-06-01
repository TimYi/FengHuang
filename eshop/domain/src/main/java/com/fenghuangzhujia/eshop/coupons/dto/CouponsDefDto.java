package com.fenghuangzhujia.eshop.coupons.dto;

import java.util.Date;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CouponsDefDto extends DtoBaseModel {

	private String name;
	private CouponsConstrainDto constrain;
	private CouponsStragyDto stragy;
	private Date expireTime;
	private boolean expired;
	private Long maxNumber;
	private Long allocatedNumber;
	private boolean usedUp;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CouponsConstrainDto getConstrain() {
		return constrain;
	}
	public void setConstrain(CouponsConstrainDto constrain) {
		this.constrain = constrain;
	}
	public CouponsStragyDto getStragy() {
		return stragy;
	}
	public void setStragy(CouponsStragyDto stragy) {
		this.stragy = stragy;
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
	public Long getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(Long maxNumber) {
		this.maxNumber = maxNumber;
	}
	public Long getAllocatedNumber() {
		return allocatedNumber;
	}
	public void setAllocatedNumber(Long allocatedNumber) {
		this.allocatedNumber = allocatedNumber;
	}
	public boolean isUsedUp() {
		return usedUp;
	}
	public void setUsedUp(boolean usedUp) {
		this.usedUp = usedUp;
	}
}
