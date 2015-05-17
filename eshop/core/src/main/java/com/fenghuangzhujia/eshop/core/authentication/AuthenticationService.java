package com.fenghuangzhujia.eshop.core.authentication;

import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

public class AuthenticationService {

	/**
	 * 获取当前登录用户，但是不带用户id，可以考虑通过getUsername接口自行获取@User
	 * @return
	 * @throws NotLoginException
	 */
	@SuppressWarnings("unchecked")
	public static User getUser() throws ErrorCodeException {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof UsernamePasswordAuthenticationToken))throw new ErrorCodeException(SystemErrorCodes.UNAUTH);
		String username=authentication.getPrincipal().toString();
		Set<Authority> authorities=(Set<Authority>)authentication.getAuthorities();
		User user=new User();
		user.setUsername(username);
		user.setAuthorities(authorities);
		return user;
	}
	
	/**
	 * 获取当前用户账户名称
	 * @return
	 * @throws NotLoginException
	 */
	public static String getUsername() throws ErrorCodeException {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof UsernamePasswordAuthenticationToken))throw new ErrorCodeException(SystemErrorCodes.UNAUTH);
		String username=authentication.getPrincipal().toString();
		return username;
	}
}
