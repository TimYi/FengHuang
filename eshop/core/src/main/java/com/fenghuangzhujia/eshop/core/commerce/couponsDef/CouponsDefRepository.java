package com.fenghuangzhujia.eshop.core.commerce.couponsDef;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface CouponsDefRepository extends SpecificationRepository<CouponsDef, String> {

	CouponsDef findByEvent(String event);
}
