package com.fenghuangzhujia.eshop.appoint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.foundation.dics.CategoryItem;

public interface AppointRepository extends PagingAndSortingRepository<Appoint, String> {
	
	Page<Appoint> findByType(CategoryItem type,Pageable pageable);
	
	Page<Appoint> findByTypeId(String typeid,Pageable pageable);
	
	Page<Appoint> findByUserId(String userid,Pageable pageable);
}
