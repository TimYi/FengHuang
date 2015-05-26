package com.fenghuangzhujia.eshop.core.commerce.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodOrderRepository extends PagingAndSortingRepository<GoodOrder, String> {
	public Page<GoodOrder> findByUserId(String userid, Pageable pageable);
}
