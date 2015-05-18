package com.fenghuangzhujia.eshop.core.authentication.authority.opration;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fenghuangzhujia.eshop.core.authentication.authority.resource.ResourceAuthority;

public interface OperationAuthorityRepository extends CrudRepository<OperationAuthority, String> {
	
	/**
	 * 按照资源获取操作
	 * @param resource
	 * @return
	 */
	public List<OperationAuthority> findByResource(ResourceAuthority resource);
	
	public List<OperationAuthority> findByResourceId(String id);
	
	public OperationAuthority getByResourceNameAndName(String resourceName, String name);
}
