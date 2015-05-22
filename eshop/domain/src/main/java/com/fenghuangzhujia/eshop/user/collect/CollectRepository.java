package com.fenghuangzhujia.eshop.user.collect;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.user.User;

public interface CollectRepository extends PagingAndSortingRepository<Collect, String> {
	
	public Page<Collect> findByUser(User user, Pageable pageable);
	
	public Page<Collect> findByUserId(String id, Pageable pageable);
}
