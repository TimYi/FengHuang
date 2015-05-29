package com.fenghuangzhujia.eshop.coupons.core;

import java.util.Date;

import com.fenghuangzhujia.foundation.core.entity.Identified;

/**
 * 优惠券
 * @author pc
 *
 */
public interface Coupons extends Identified<String> {
	
	/**
	 * 获取优惠券名称
	 * @return
	 */
	String getName();
	
	/**
	 * 获取优惠券定义，可选
	 * @return
	 */
	CouponsDef getDef();

	/**
	 * 优惠券限制规则
	 * @return
	 */
	CouponsConstrain getConstrain();
	
	/**
	 * 以字符量显示限制规则
	 * @return
	 */
	String getConstrainType();
	
	/**
	 * 获取限制类型名称
	 * @return
	 */
	String getConstrainName();
	
	/**
	 * 优惠策略
	 * @return
	 */
	CouponsStragy getStragy();
	
	/**
	 * 获取优惠名称
	 * @return
	 */
	String getStragyName();
	
	/**
	 * 获取过期时间
	 * @return
	 */
	Date getExpireTime();
	
	/**
	 * 是否已经过期
	 * @return
	 */
	boolean isExpired();
	
	/**
	 * 是否已经使用
	 * @return
	 */
	boolean isUsed();
}
