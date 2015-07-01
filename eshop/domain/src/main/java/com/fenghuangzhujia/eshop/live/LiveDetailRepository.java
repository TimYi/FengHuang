package com.fenghuangzhujia.eshop.live;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface LiveDetailRepository extends SpecificationRepository<LiveDetail, String> {

	Page<LiveDetail> findByLiveId(String liveId, Pageable pageable);
	
	Page<LiveDetail> findByLive(Live live, Pageable pageable);
}
