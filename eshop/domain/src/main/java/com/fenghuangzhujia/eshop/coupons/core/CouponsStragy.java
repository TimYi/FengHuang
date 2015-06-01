package com.fenghuangzhujia.eshop.coupons.core;

import com.fenghuangzhujia.foundation.core.entity.Identified;

/**
 * 具体优惠策略
 * @author pc
 *
 */
public interface CouponsStragy extends Identified<String> {

	/**
	 * 优惠策略名称
	 * @return
	 */
	String getName();
	
	/**
	 * 优惠策略类型
	 * @return
	 */
	String getType();
	
	/**
	 * 计算具体的优惠数额
	 * @param args 代表应用场景，比如有上限的打折优惠，args为购买商品的总金额
	 * @return
	 */
	Double getCoupons(Object[] args);
}
