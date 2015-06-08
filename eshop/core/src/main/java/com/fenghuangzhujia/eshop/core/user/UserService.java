package com.fenghuangzhujia.eshop.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityRepository;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleRepository;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class UserService extends DtoSpecificationService<User, UserDto, UserInputArgs, String> {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	
	/**
	 * 根据用户名称获取用户实体
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	public User getByUsername(String username) {
		return getRepository().getByUsername(username);
	}
	
	@Override
	public UserDto add(UserInputArgs t) {
		String username=t.getUsername();
		User user=getRepository().getByUsername(username);
		if(user!=null) {
			throw new RuntimeException("用户名重复！");
		}
		user=adapter.convertToDo(t);
		encryptPassword(user);
		getRepository().save(user);
		return adapter.convert(user);
	}
	
	@Override
	public UserDto update(UserInputArgs t) {		
		User user=getRepository().findOne(t.getId());
		//要处理密码更新问题
		String password=user.getPassword();
		String newPassword=t.getPassword();
		user=adapter.update(t, user);
		if(password.equals(newPassword)) {
			user.setPassword(password);
		} else {
			encryptPassword(user);
		}
		getRepository().save(user);
		return adapter.convert(user);
	}
	
	private void encryptPassword(User user) {
		String password=AuthenticationManager.ENCODER.encode(user.getPassword());
		user.setPassword(password);
	}
	
	@Autowired
	public void setUserRepository(UserRepository repository) {
		super.setRepository(repository);
	}
	@Override
	public UserRepository getRepository() {
		return (UserRepository)super.getRepository();
	}
}



