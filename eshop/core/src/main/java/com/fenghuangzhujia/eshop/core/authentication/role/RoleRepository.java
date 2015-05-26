package com.fenghuangzhujia.eshop.core.authentication.role;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
	
	Role getByName(String name);
}
