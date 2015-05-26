package com.fenghuangzhujia.eshop.core.commerce.cargo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.user.User;

public interface CargoOrderRepository extends PagingAndSortingRepository<CargoOrder, String> {
	Page<CargoOrder> findByUser(User user, Pageable pageable);
	
	Page<CargoOrder> findByUserId(String id, Pageable pageable);
}
