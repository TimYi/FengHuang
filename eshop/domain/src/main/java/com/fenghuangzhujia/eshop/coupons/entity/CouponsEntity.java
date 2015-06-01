package com.fenghuangzhujia.eshop.coupons.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.coupons.core.Coupons;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_coupons")
public class CouponsEntity extends UUIDBaseModel implements Coupons {

	private User user;
	private String name;
	private CouponsDefEntity def;
	private CouponsConstrainEntity constrain;
	private CouponsStragyEntity stragy;
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
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	@ManyToOne
	public CouponsDefEntity getDef() {
		return def;
	}
	public void setDef(CouponsDefEntity def) {
		this.def = def;
	}

	@Override
	@ManyToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	public CouponsConstrainEntity getConstrain() {
		return constrain;
	}
	public void setConstrain(CouponsConstrainEntity constrain) {
		this.constrain = constrain;
	}

	@Transient
	@Override
	public String getConstrainType() {
		return getConstrain().getType();
	}

	@Transient
	@Override
	public String getConstrainName() {
		return getConstrain().getName();
	}

	@Override
	@ManyToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	public CouponsStragyEntity getStragy() {
		return stragy;
	}
	public void setStragy(CouponsStragyEntity stragy) {
		this.stragy = stragy;
	}

	@Transient
	@Override
	public String getStragyType() {
		return getStragy().getType();
	}

	@Transient
	@Override
	public String getStragyName() {
		return getStragy().getName();
	}

	@Override
	public Double getCouponsMoney() {
		return couponsMoney;
	}
	public void setCouponsMoney(Double couponsMoney) {
		this.couponsMoney = couponsMoney;
	}

	@Override
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	@Override
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
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
	
	/**
	 * 优惠在指定场景是否可用
	 */
	@Transient
	@Override
	public boolean couldUse(Object[] args) {
		return getConstrain().couldUse(args);
	}
	
	/**
	 * 优惠在指定场景下的优惠金额
	 */
	@Transient
	@Override
	public Double getCoupons(Object[] args) {
		return getStragy().getCoupons(args);
	}
}
