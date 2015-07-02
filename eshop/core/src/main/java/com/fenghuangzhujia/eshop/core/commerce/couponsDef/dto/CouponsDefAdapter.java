package com.fenghuangzhujia.eshop.core.commerce.couponsDef.dto;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.commerce.couponsDef.CouponsDef;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class CouponsDefAdapter extends AbstractDtoAdapter<CouponsDef, CouponsDefDto, CouponsDefInputArgs> {

	@Override
	public CouponsDefDto postConvert(CouponsDef d, CouponsDefDto t) {
		return t;
	}

	@Override
	public CouponsDef postConvertToDo(CouponsDefInputArgs i, CouponsDef d) {
		return d;
	}

	@Override
	public CouponsDef postUpdate(CouponsDefInputArgs i, CouponsDef d) {
		return d;
	}
}
