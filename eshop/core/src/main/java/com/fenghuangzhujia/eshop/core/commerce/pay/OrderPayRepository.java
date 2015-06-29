package com.fenghuangzhujia.eshop.core.commerce.pay;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface OrderPayRepository extends SpecificationRepository<OrderPay, String> {

	OrderPay getByPufaPay(PufaPay pufaPay);
}
