package com.fenghuangzhujia.eshop.coupons.core;

import java.util.Date;

import com.fenghuangzhujia.foundation.core.entity.Identified;

/**
 * 优惠券定义，定义优惠券生成规则
 * @author pc
 *
 */
public interface CouponsDef extends Identified<String> {
	
	/**
	 * 获取优惠券名称
	 * @return
	 */
	String getName();

	/**
	 * 优惠券限制规则
	 * @return
	 */
	CouponsConstrain getConstrain();
	
	/**
	 * 优惠策略
	 * @return
	 */
	CouponsStragy getStragy();
	
	/**
	 * 获取过期时间
	 * @return
	 */
	Date getExpireTime();
	
	/**
	 * 分发优惠券数量上限
	 * @return
	 */
	Long getMaxNumber();
	
	/**
	 * 已经分发的优惠券数量
	 * @return
	 */
	Long getAllocatedNumber();
	
	/**
	 * 是否能够继续分发优惠券
	 * @return
	 */
	boolean couldAllocate();
	
	/**
	 * 生成优惠券
	 * @return
	 */
	Coupons createCoupons(); 
}
