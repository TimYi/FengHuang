package com.fenghuangzhujia.eshop.core.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

public class AuthenticationService {	
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
	
	public static SimpleUserDetails getUserDetail() throws ErrorCodeException {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof UsernamePasswordAuthenticationToken))throw new ErrorCodeException(SystemErrorCodes.UNAUTH);
		SimpleUserDetails userDetail=(SimpleUserDetails)authentication.getDetails();
		return userDetail;
	}
}
