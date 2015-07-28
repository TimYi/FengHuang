package com.fenghuangzhujia.eshop.admin.user;

import org.springframework.data.repository.CrudRepository;

public interface SystemTokenRepository extends CrudRepository<SystemToken, String> {

	SystemToken getByToken(String token);
	
	SystemToken getByUser(SystemUser user);
}
