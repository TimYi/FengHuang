package com.fenghuangzhujia.eshop.core.commerce.coupons;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.remind.impl.UnreadRemindEntity;
import com.fenghuangzhujia.eshop.core.user.User;

@Entity
@Table(name="fhzj_coupons")
public class Coupons extends UnreadRemindEntity {

	private User user;
	private String name;
	private Double couponsMoney;
	private Date expireTime;
	private boolean expired;
	private boolean used;
	
	/**
	 * 优惠券所属用户
	 * @return
	 */
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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
		if(expireTime==null)return false;
		Date date=new Date();
		if(date.before(expireTime))return false;
		setExpired(true);
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
}
