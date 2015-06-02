package com.fenghuangzhujia.eshop.core.authentication;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.token.TokenRepository;
import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.utils.Identities;
import com.fenghuangzhujia.foundation.utils.PhoneNumberValidator;

@Service(value="authenticateService")
@Transactional
public class BasicAuthenticationManager implements AuthenticationManager {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenRepository tokenRepository;
	
	/**
	 * 确保加载全部权限信息
	 */
	@Override
	public SimpleUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user=userRepository.getByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("not found");
		}
		return new SimpleUserDetails(user);
	}
	
	/**
	 * 确保加载全部权限信息
	 */
	@Override
	public SimpleUserDetails authenticate(String token) throws ErrorCodeException {
		UserToken tk=tokenRepository.getByToken(token);
		if(tk==null) {
			throw new ErrorCodeException(TOKEN_ERROR);
		}
		User user=tk.getUser();
		//保证用户通过认证
		if(!user.isEnabled())throw new ErrorCodeException(TOKEN_ERROR,"该用户尚未通过认证");
		return new SimpleUserDetails(user);
	}

	@Override
	public UserToken login(String username, String password) throws ErrorCodeException {
		User user=userRepository.getByUsername(username);
		if(user==null) {
			throw new ErrorCodeException(LOGIN_ERROR,"用户名不存在");
		}
		//确保用户经过认证
		if(!user.isEnabled())throw new ErrorCodeException(LOGIN_ERROR, "用户尚未通过认证，请先注册");
		String pwd=user.getPassword();
		if(!ENCODER.matches(password, pwd)) {
			throw new ErrorCodeException(LOGIN_ERROR, "密码错误，请重新输入");
		}
		UserToken token=tokenRepository.getByUser(user);
		if(token==null) {
			token=new UserToken();		
			token.setUser(user);
			token=refreshToken(token);
			return token;
		}
		token=refreshToken(token);
		return token;
	}

	@Override
	public UserToken regist(String username, String password)
			throws ErrorCodeException {
		try {
			checkAccount(username);
			checkPassword(password);
		} catch (Exception e) {
			throw new ErrorCodeException(REGIST_ERROR, e);
		}
		User user=userRepository.getByUsername(username);
		if(user==null) {
			user=new User();
			user.setUsername(username);		
		} else {
			if(!user.isEnabled()) {
				throw new ErrorCodeException(REGIST_ERROR, "该用户已经注册过，请直接登录！");
			}
		}
		user.setPassword(password);
		user.setVerified(true);
		entryptPassword(user);
		user=userRepository.save(user);
		UserToken token=tokenRepository.getByUser(user);
		if(token==null){
			token=new UserToken();
			token.setUser(user);
		}
		token=refreshToken(token);
		return token;
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		String password=entryptPassword(user.getPassword());
		user.setPassword(password);
	}
	
	private String entryptPassword(String password) {
		return ENCODER.encode(password);
	}

	@Override
	public UserToken changePassword(String username, String newPassword,
			String oldPassword) throws ErrorCodeException {
		try {
			UserToken t=login(username, oldPassword);
			User user=t.getUser();
			checkPassword(newPassword);
			user.setPassword(newPassword);
			entryptPassword(user);
			userRepository.save(user);
			return t;
		} catch (Exception e) {
			throw new ErrorCodeException(CHANGE_PASSWORD_ERROR, e);
		}
	}
	
	/**
	 * 生成新token，并将更新持久化。
	 * @param token
	 * @return
	 */
	protected UserToken refreshToken(UserToken token) {
		String tokenString=Identities.uuid2();
		token.setToken(tokenString);
		tokenRepository.save(token);
		return token;
	}
	
	
	/**
	 * 检查账号，如果符合规则，返回true
	 * @param account
	 * @return
	 */
	protected void checkAccount(String account) throws Exception {
		if(!PhoneNumberValidator.isMobile(account)) {
			throw new Exception("请输入正确的11位手机号码！");
		}
	}
	/**
	 * 检查密码，如果符合规则，返回true
	 * @param account
	 * @return
	 */
	protected void checkPassword(String password) throws Exception {
		
	}
}
