package com.fenghuangzhujia.eshop.admin.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.admin.auth.SystemAuthManager;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.*;

@Service
@Transactional
public class SystemUserManager {

	@Autowired
	private SystemUserRepository repository;
	
	public SystemUser add(SystemUser user) {
		String username=user.getUsername();
		SystemUser duplicate=repository.getByUsername(username);
		if(duplicate!=null) {
			throw new ErrorCodeException(REGIST_ERROR,"用户名已使用");
		}
		String password=user.getPassword();
		password=SystemAuthManager.encryptPassword(password);
		user.setPassword(password);
		user=repository.save(user);
		return user;
	}
	
	public SystemUser update(String id, String username, String password, String realname, boolean verified) {
		SystemUser user=repository.findOne(id);
		if(!user.getUsername().equals(username)) {
			SystemUser duplicate=repository.getByUsername(username);
			if(duplicate!=null) {
				throw new ErrorCodeException(REGIST_ERROR,"用户名已使用");
			}
		}
		user.setUsername(username);
		if(StringUtils.isNotBlank(password)) {
			password=SystemAuthManager.encryptPassword(password);
			user.setPassword(password);
		}		
		user.setRealname(realname);
		user.setVerified(verified);
		return user;
	}
}
