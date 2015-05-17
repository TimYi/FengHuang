package com.fenghuangzhujia.eshop.core.commerce.eshop;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShopRepository extends PagingAndSortingRepository<Shop, String> {
	Shop getByName(String name);
}
