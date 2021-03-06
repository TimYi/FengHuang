package com.fenghuangzhujia.eshop.core.couponsDef;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.coupons.Coupons;
import com.fenghuangzhujia.eshop.core.coupons.CouponsRepository;
import com.fenghuangzhujia.eshop.core.coupons.dto.CouponsAdapter;
import com.fenghuangzhujia.eshop.core.coupons.dto.CouponsDto;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

/**
 * 优惠券分发器
 * 根据不同的触发事件类型，从数据库读取优惠策略
 * 根据优惠策略，向用户分发优惠券
 * @author pc
 *
 */
@Component
@Transactional
public class CouponsAllocater {
	
	//以下为目前约定的事件类型：
	/**注册*/
	public static final String REGIST="regist";
	/**套餐抢购成功*/
	public static final String SCRAMBLE="scramble";
	/**支付订单成功*/
	public static final String PAY_ORDER="payOrder";
	/**预约服务成功*/
	public static final String APPOINT_SERVICE="appointService";
	/**预约套餐成功*/
	public static final String APPOINT_PACKAGE="appointPackage";
	
	/**
	 * 汇总所有优惠券事件，key为事件编码，value为事件描述
	 */
	public static final Map<String, String> COUPONS_EVENTS=new HashMap<String, String>();
	
	static {
		COUPONS_EVENTS.put(REGIST, "新用户注册");
		COUPONS_EVENTS.put(SCRAMBLE, "成功抢购套餐");
		COUPONS_EVENTS.put(PAY_ORDER, "成功支付订单");
		COUPONS_EVENTS.put(APPOINT_SERVICE, "成功预约服务");
		COUPONS_EVENTS.put(APPOINT_PACKAGE, "成功预约套餐");
	}

	@Autowired
	private CouponsDefRepository defRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CouponsRepository couponsRepository;
	@Autowired
	private CouponsAdapter couponsAdapter;
	
	/**
	 * 根据不同的触发事件类型，从数据库读取优惠策略
	 * 根据优惠策略，向用户分发优惠券
	 * @param event
	 * @param userId
	 */
	public void allocate(String event, String userId) {
		CouponsDef def=defRepository.findByEvent(event);
		if(def==null || !def.isInUse())return;
		User user=userRepository.findOne(userId);
		Coupons coupons=def.generateCoupons(user);
		couponsRepository.save(coupons);
	}
	
	/**
	 * 临时用于处理优惠券抢购
	 * @param userId
	 * @return 是否抢购成功
	 */
	public CouponsDto scramble(String userId, String event) {
		CouponsDef def=defRepository.findByEvent(event);
		if(def==null)throw new ErrorCodeException(SystemErrorCodes.OTHER, "还没有开始优惠券抢购活动，敬请期待");
		/** 临时去掉优惠券抢购限制
		List<Coupons> qgCouponses=couponsRepository.findByUserIdAndType(userId, event);
		if(qgCouponses==null || !qgCouponses.isEmpty())
			throw new ErrorCodeException(SystemErrorCodes.OTHER, "您已经抢购过一张优惠券");
			*/
		User user=userRepository.findOne(userId);
		Coupons coupons=def.generateCoupons(user);
		if(coupons==null)throw new ErrorCodeException(SystemErrorCodes.OTHER, "优惠券已经抢光喽");
		coupons=couponsRepository.save(coupons);
		CouponsDto result=couponsAdapter.convertToDetailedDto(coupons);
		return result;
	}
}
