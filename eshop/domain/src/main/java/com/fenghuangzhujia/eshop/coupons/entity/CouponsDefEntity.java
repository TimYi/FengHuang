package com.fenghuangzhujia.eshop.coupons.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.coupons.core.CouponsDef;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_couponse_def")
public class CouponsDefEntity extends UUIDBaseModel implements CouponsDef {

	private String name;
	private CouponsConstrainEntity constrain;
	private CouponsStragyEntity stragy;
	private Date expireTime;
	private boolean expired;
	private Long maxNumber;
	private Long allocatedNumber;
	private boolean usedUp;
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 优惠限制，外键一对一关联
	 */
	@Override
	@OneToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	public CouponsConstrainEntity getConstrain() {
		return constrain;
	}
	public void setConstrain(CouponsConstrainEntity constrain) {
		this.constrain = constrain;
	}

	/**
	 * 优惠策略外键一对一关联
	 */
	@OneToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@Override
	public CouponsStragyEntity getStragy() {
		return stragy;
	}
	public void setStragy(CouponsStragyEntity stragy) {
		this.stragy = stragy;
	}

	@Override
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@Override
	public Long getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(Long maxNumber) {
		this.maxNumber = maxNumber;
	}

	@Override
	public Long getAllocatedNumber() {
		return allocatedNumber;
	}
	public void setAllocatedNumber(Long allocatedNumber) {
		this.allocatedNumber = allocatedNumber;
	}
	
	@Override
	public boolean isUsedUp() {
		if(isExpired()) {
			setUsedUp(true);
			return usedUp;
		}
		if(maxNumber==null) {
			return usedUp;
		}
		if(allocatedNumber==null || allocatedNumber<maxNumber) {
			return usedUp;
		}
		setUsedUp(true);
		return usedUp;
	}
	public void setUsedUp(boolean usedUp) {
		this.usedUp = usedUp;
	}
	
	/**
	 * 判断是否过期
	 * @return
	 */
	@Override
	@Transient
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
	 * 如果不能继续分配，则返回空值
	 * @return
	 */
	@Transient
	@Override
	public CouponsEntity createCoupons() {
		if(isUsedUp())return null;
		if(maxNumber!=null) {
			//占用一个allocatedNumber
			if(allocatedNumber==null)allocatedNumber=0L;
			setAllocatedNumber(allocatedNumber+1);
		}
		CouponsEntity entity=new CouponsEntity();
		entity.setConstrain(constrain);
		entity.setDef(this);
		entity.setName(name);
		entity.setStragy(stragy);
		entity.setExpireTime(expireTime);
		entity.setUsed(false);
		return entity;
	}
}
