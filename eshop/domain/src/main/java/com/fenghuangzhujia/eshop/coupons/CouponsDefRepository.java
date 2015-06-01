package com.fenghuangzhujia.eshop.coupons;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.coupons.entity.CouponsDefEntity;

public interface CouponsDefRepository extends 
	PagingAndSortingRepository<CouponsDefEntity, String> {

}
