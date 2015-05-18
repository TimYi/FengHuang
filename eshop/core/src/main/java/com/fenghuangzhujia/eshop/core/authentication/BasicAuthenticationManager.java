package com.fenghuangzhujia.eshop.core.authentication;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.authority.concrete.ConcreteAuthority;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.token.TokenRepository;
import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
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
	public User loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user=userRepository.getByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("not found");
		}
		user=loadAuthorities(user);
		return user;
	}
	
	/**
	 * 确保加载全部权限信息
	 */
	@Override
	public User authenticate(String token) throws ErrorCodeException {
		UserToken tk=tokenRepository.getByToken(token);
		if(tk==null) {
			throw new ErrorCodeException(TOKEN_ERROR);
		}
		User user=tk.getUser();
		//保证用户通过认证
		if(!user.isEnabled())throw new ErrorCodeException(TOKEN_ERROR,"该用户尚未通过认证");
		user=loadAuthorities(user);
		return user;
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
		if(!pwd.equals(password)) {
			throw new ErrorCodeException(LOGIN_ERROR, "密码错误，请重新输入");
		}
		UserToken token=tokenRepository.getByUser(user);
		if(token==null) {
			token=new UserToken();			
			tokenRepository.save(token);
			token=refreshToken(token);
			return token;
		}
		token=refreshToken(token);
		return token;
	}
	
	@Override
	public void preRegist(String username, String password)
			throws Exception {
		User user=userRepository.getByUsername(username);
		//不能覆盖已有账户
		if(user!=null) {
			return;			
		}
		try {
			checkAccount(username);
		} catch (Exception e) {
			throw new Exception(e);
		}
		user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setVerified(false);
		userRepository.save(user);		
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
		userRepository.save(user);
		UserToken token=tokenRepository.getByUser(user);
		if(token==null){
			token=new UserToken();
			tokenRepository.save(token);
		}
		token=refreshToken(token);
		return token;
	}

	@Override
	public UserToken changePassword(String username, String newPassword,
			String oldPassword) throws ErrorCodeException {
		try {
			UserToken t=login(username, oldPassword);
			User user=t.getUser();
			checkPassword(newPassword);
			user.setPassword(newPassword);
			userRepository.save(user);
			return t;
		} catch (Exception e) {
			throw new ErrorCodeException(CHANGE_PASSWORD_ERROR, e);
		}
	}
	
	/**
	 * 从User的role中解析User拥有的全部权限
	 * @param user
	 * @return
	 */
	protected User loadAuthorities(User user) {
		Set<Role> roles=user.getRoles();
		if(roles==null) {
			return user;
		}
		Set<ConcreteAuthority> authorities=new HashSet<>();
		for (Role role : roles) {
			Set<ConcreteAuthority> roleAuthorities=role.getAuthorities();
			if(roleAuthorities==null)continue;
			authorities.addAll(roleAuthorities);
		}
		user.setAuthorities(authorities);		
		return user;
	}	
	
	/**
	 * 生成新token，并将更新持久化。
	 * @param token
	 * @return
	 */
	protected UserToken refreshToken(UserToken token) {
		UUID uuid=UUID.randomUUID();
		String tokenString=uuid.toString();
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
