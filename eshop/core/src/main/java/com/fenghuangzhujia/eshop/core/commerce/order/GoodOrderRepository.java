package com.fenghuangzhujia.eshop.core.commerce.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;

public interface GoodOrderRepository extends PagingAndSortingRepository<GoodOrder, String> {
	public Page<GoodOrder> findByUserId(String userid, Pageable pageable);
	
	public Page<GoodOrder> findByStatus(OrderStatus status, Pageable pageable);
	
	public Page<GoodOrder> findByUserIdAndStatus(String userid, OrderStatus status, Pageable pageable);
}
