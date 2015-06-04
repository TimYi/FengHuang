package com.fenghuangzhujia.eshop.collect;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CollectRepository extends PagingAndSortingRepository<Collect, String> {
	
	public Page<Collect> findByUserId(String id, Pageable pageable);
}
