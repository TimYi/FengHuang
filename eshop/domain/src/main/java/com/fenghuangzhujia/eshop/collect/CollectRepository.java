package com.fenghuangzhujia.eshop.collect;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.eshop.common.remind.impl.UnreadRemindPagingRepository;

public interface CollectRepository extends UnreadRemindPagingRepository<Collect, String> {
	
	public Page<Collect> findByUserId(String id, Pageable pageable);
}
