package com.fenghuangzhujia.eshop.coupons.core;

/**
 * 优惠券管理器
 * 处理业务流程相关内容
 * @author pc
 *
 */
public interface CouponsManager {

	/**
	 * 根据优惠券定义，尝试向用户分配一个优惠券
	 * @param userid
	 * @param defid
	 */
	public Coupons allocate(String userid, String defid);
}
