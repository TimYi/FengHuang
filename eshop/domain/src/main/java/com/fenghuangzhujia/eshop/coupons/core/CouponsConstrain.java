package com.fenghuangzhujia.eshop.coupons.core;

import com.fenghuangzhujia.foundation.core.entity.Identified;

/**
 * 优惠券使用限制
 * @author pc
 *
 */
public interface CouponsConstrain extends Identified<String> {

	String getType();
	
	/**
	 * 获取限制名称
	 * @return
	 */
	String getName();
	
	/**
	 * 判断优惠券是否可用
	 * @param args 表示应用场景的参数
	 * @return
	 */
	boolean couldUse(Object[] args);
}
