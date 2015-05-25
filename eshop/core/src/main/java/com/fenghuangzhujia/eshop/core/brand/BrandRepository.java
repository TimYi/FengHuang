package com.fenghuangzhujia.eshop.core.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepository extends PagingAndSortingRepository<Brand, String> {
	
	/**
	 * 根据品牌id获取brand列表
	 * @param id
	 * @param pageable
	 * @return
	 */
	public Page<Brand> findByBrandTypeId(String id, Pageable pageable);
}
