package com.fenghuangzhujia.eshop.live;

import java.util.List;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface LiveRepository extends SpecificationRepository<Live, String> {
	
	List<Live> findByUserId(String userId);
}
