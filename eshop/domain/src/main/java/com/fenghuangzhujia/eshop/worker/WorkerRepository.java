package com.fenghuangzhujia.eshop.worker;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface WorkerRepository extends SpecificationRepository<Worker, String> {

	Worker getByUserId(String userid);
}
