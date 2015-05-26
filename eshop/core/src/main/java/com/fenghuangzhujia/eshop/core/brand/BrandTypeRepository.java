package com.fenghuangzhujia.eshop.core.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandTypeRepository extends PagingAndSortingRepository<BrandType, String> {
	
	/**
	 * 获取一级品牌类型
	 * @param pageable
	 * @return
	 */
	@Query("select t from BrandType t where t.superType is null")
	public Page<BrandType> findTopBrandTypes(Pageable pageable);
}
