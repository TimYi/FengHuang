package com.fenghuangzhujia.eshop.core.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;


public interface AuthenticationManager extends UserDetailsService {
	
	/**
	 * 需要保证返回的User中加载了全部权限信息
	 */
	@Override
	SimpleUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	/**
	 * 用户登录并获取返回token
	 * @param username
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	UserToken login(String username, String password) throws ErrorCodeException;
	
	/**
	 * 通过token验证用户身份，返回User之前，一定要将所有 @BasicAuthority 提前计算赋值。可以有缓存算法。
	 * @param token
	 * @return
	 * @throws TokenErrorException
	 */
	SimpleUserDetails authenticate(String token) throws ErrorCodeException;
	
	/**
	 * 在某些情况下允许用户预注册，但是标记用户isValidated为false，不分配任何角色
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	void preRegist(String username, String password) throws Exception;
	
	/**
	 * 注册新用户，并返回token
	 * @param username
	 * @param password
	 * @return
	 * @throws RegistException
	 */
	UserToken regist(String username, String password) throws ErrorCodeException;
	
	/**
	 * 用户自行变更密码
	 * @param username
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 * @throws ChangePasswordException
	 */
	UserToken changePassword(String username, String newPassword, String oldPassword) throws ErrorCodeException;
}
