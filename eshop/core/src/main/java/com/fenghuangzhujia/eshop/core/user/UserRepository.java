package com.fenghuangzhujia.eshop.core.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	User getByUsername(String username);
}
