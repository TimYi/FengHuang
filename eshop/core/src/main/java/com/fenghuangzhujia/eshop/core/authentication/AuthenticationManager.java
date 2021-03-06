package com.fenghuangzhujia.eshop.core.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;


public interface AuthenticationManager extends UserDetailsService {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	public static final BCryptPasswordEncoder ENCODER=new BCryptPasswordEncoder();
	
	/**
	 * 需要保证返回的User中加载了全部权限信息
	 */
	@Override
	SimpleUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	/**
	 * 用户登录并获取返回token
	 * @param username
	 * @param password
	 * @param ip 登录ip地址
	 * @return
	 * @throws LoginException
	 */
	UserToken login(String username, String password, String ip) throws ErrorCodeException;
	
	void logout(String token);
	
	/**
	 * 通过token验证用户身份，返回User之前，一定要将所有 @BasicAuthority 提前计算赋值。可以有缓存算法。
	 * @param token
	 * @return
	 * @throws TokenErrorException
	 */
	SimpleUserDetails authenticate(String token) throws ErrorCodeException;
	
	/**
	 * 注册新用户，并返回token
	 * @param username 2~10位，以字母或者汉字开头，由字母、数字或者汉字组成的用户名。
	 * @param password
	 * @param ip 注册ip地址
	 * @return
	 * @throws RegistException
	 */
	UserToken regist(String username, String password, String mobile, String ip) throws ErrorCodeException;
	
	/**
	 * 用户自行变更密码
	 * @param username
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 * @throws ChangePasswordException
	 */
	UserToken changePassword(String username, String newPassword, String oldPassword) throws ErrorCodeException;
	
	/**
	 * 忘记密码后，通过手机号码申请修改密码
	 * @param username
	 * @param telephone
	 * @param validater
	 * @return 允许修改密码凭证
	 * @throws ErrorCodeException
	 */
	String forgetPassword(String username, String telephone) throws ErrorCodeException;
	
	/**
	 * 忘记密码找回
	 * @param username
	 * @param validater
	 * @param newPassword
	 * @throws ErrorCodeException
	 */
	void changeForgotPassword(String username, String validater, String newPassword) throws ErrorCodeException;
	
	public static String encryptPassword(String password) {
		return ENCODER.encode(password);
	}
}
