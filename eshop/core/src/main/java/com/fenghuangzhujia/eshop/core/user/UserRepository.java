package com.fenghuangzhujia.eshop.core.user;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface UserRepository extends SpecificationRepository<User, String> {
	
	User getByUsername(String username);
	
	User getByMobile(String mobile);
}
