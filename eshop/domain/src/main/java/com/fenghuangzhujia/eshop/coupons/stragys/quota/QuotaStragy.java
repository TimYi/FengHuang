package com.fenghuangzhujia.eshop.coupons.stragys.quota;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.coupons.entity.CouponsStragyEntity;

/**
 * 定额优惠策略
 * @author pc
 *
 */
@Entity
@DiscriminatorValue(value="QUOTA")
public class QuotaStragy extends CouponsStragyEntity {
	
	private Double couponsMoney;

	public Double getCouponsMoney() {
		return couponsMoney;
	}
	public void setCouponsMoney(Double couponsMoney) {
		this.couponsMoney = couponsMoney;
	}

	@Transient
	@Override
	public Double getCoupons(Object[] args) {
		return couponsMoney;
	}
}
