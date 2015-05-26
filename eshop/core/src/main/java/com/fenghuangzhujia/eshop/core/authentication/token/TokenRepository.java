package com.fenghuangzhujia.eshop.core.authentication.token;

import org.springframework.data.repository.CrudRepository;

import com.fenghuangzhujia.eshop.core.user.User;

public interface TokenRepository extends CrudRepository<UserToken, String> {
	public UserToken getByToken(String token);
	
	public UserToken getByUser(User user);
}
