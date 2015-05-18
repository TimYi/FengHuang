package com.fenghuangzhujia.eshop.core.authentication.authority.resource;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResourceAuthorityRepository extends PagingAndSortingRepository<ResourceAuthority, String> {
	
	/**
	 * 按照资源名称获取Authority
	 * @param name
	 * @return
	 */
	public ResourceAuthority getByName(String name);
}
