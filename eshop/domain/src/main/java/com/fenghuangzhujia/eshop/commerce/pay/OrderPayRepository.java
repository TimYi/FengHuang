package com.fenghuangzhujia.eshop.commerce.pay;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface OrderPayRepository extends SpecificationRepository<OrderPay, String> {

	OrderPay getByPufaPay(PufaPay pufaPay);
}
