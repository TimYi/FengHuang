package com.fenghuangzhujia.eshop.core.authentication.authority.concrete;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConcreteAuthorityRepository extends PagingAndSortingRepository<ConcreteAuthority, String> {
	
	@Query("select a from ConcreteAuthority a join a.operation o join o.resource r where r.name=?1 and o.name=?2 and a.resourceid=?3")
	public ConcreteAuthority getOne(String resourceName, String operationName, String resourceid);
}
