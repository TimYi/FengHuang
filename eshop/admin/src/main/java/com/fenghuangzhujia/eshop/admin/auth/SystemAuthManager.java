package com.fenghuangzhujia.eshop.admin.auth;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.LOGIN_ERROR;
import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.TOKEN_ERROR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.admin.user.SystemToken;
import com.fenghuangzhujia.eshop.admin.user.SystemTokenRepository;
import com.fenghuangzhujia.eshop.admin.user.SystemUser;
import com.fenghuangzhujia.eshop.admin.user.SystemUserDetails;
import com.fenghuangzhujia.eshop.admin.user.SystemUserRepository;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.security.rest.RestAuthManager;
import com.fenghuangzhujia.foundation.utils.Identities;

@Service
@Transactional
public class SystemAuthManager implements RestAuthManager {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	public static final BCryptPasswordEncoder ENCODER=new BCryptPasswordEncoder();
	
	public static String encryptPassword(String password) {
		return ENCODER.encode(password);
	}
	
	@Autowired
	private SystemUserRepository userRepository;
	@Autowired
	private SystemTokenRepository tokenRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SystemUser user=userRepository.getByUsername(username);
		if(user==null) throw new UsernameNotFoundException("用户名不存在");
		SystemUserDetails details=new SystemUserDetails(user);
		return details;
	}

	@Override
	public SystemToken login(String principal, String credential) {
		SystemUser user=userRepository.getByUsername(principal);
		if(user==null) {
			throw new ErrorCodeException(LOGIN_ERROR,"用户名不存在");
		}
		//确保用户经过认证
		if(!user.isVerified())throw new ErrorCodeException(LOGIN_ERROR, "用户尚未通过认证，请先注册");
		String pwd=user.getPassword();
		if(!ENCODER.matches(credential, pwd)) {
			throw new ErrorCodeException(LOGIN_ERROR, "密码错误，请重新输入");
		}	
		SystemToken token=tokenRepository.getByUser(user);
		if(token==null) {
			token=new SystemToken();		
			token.setUser(user);
			token=refreshToken(token);
			return token;
		}
		token=refreshToken(token);
		return token;
	}

	@Override
	public void logout(String token) {
		SystemToken tk=tokenRepository.getByToken(token);
		if(tk!=null) {
			tokenRepository.delete(tk);
		}
	}

	@Override
	public UserDetails authenticate(String token) {
		SystemToken tk=tokenRepository.getByToken(token);
		if(tk==null) {
			throw new ErrorCodeException(TOKEN_ERROR);
		}
		SystemUser user=tk.getUser();
		//保证用户通过认证
		if(!user.isVerified())throw new ErrorCodeException(TOKEN_ERROR,"该用户尚未通过认证");
		return new SystemUserDetails(user);
	}
	
	/**
	 * 生成新token，并将更新持久化。
	 * @param token
	 * @return
	 */
	protected SystemToken refreshToken(SystemToken token) {
		String tokenString=Identities.uuid2();
		token.setToken(tokenString);
		token=tokenRepository.save(token);
		return token;
	}
}
