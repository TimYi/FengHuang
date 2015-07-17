package com.fenghuangzhujia.eshop.commerce.pay;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface PufaPayRepository extends SpecificationRepository<PufaPay, String> {

	PufaPay getByTermSsn(String termSsn);
}
