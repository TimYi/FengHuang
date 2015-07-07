package com.fenghuangzhujia.eshop.core.authentication.role;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityRepository;
import com.fenghuangzhujia.eshop.core.authentication.role.dto.RoleDto;
import com.fenghuangzhujia.eshop.core.authentication.role.dto.RoleInputArgs;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class RoleService extends DtoPagingService<Role, RoleDto, RoleInputArgs, String> {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private UserRepository userRepository;
	
	public RoleDto getByName(String name) {
		Role role=getRepository().getByName(name);
		return adapter.convert(role);
	}
	
	/**
	 * 添加某种角色的用户
	 * @param username
	 * @param password
	 * @param roleName
	 */
	public void addRoleUser(String username, String password, String roleName) {
		Role role=getRepository().getByName(roleName);
		User user=userRepository.getByUsername(username);
		if(user!=null) {
			throw new RuntimeException("用户名重复！");
		}
		user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setVerified(true); //默认新建用户自动通过审核
		encryptPassword(user);
		user=userRepository.save(user);
		Set<User> users=role.getUsers();
		if(users==null)users=new HashSet<>();
		users.add(user);
		role.setUsers(users);
		getRepository().save(role);
	}
	
	public void removeUser(String id, String userId) {
		Role role=getRepository().findOne(id);
		User user=userRepository.findOne(userId);
		role.getUsers().remove(user);
		getRepository().save(role);
	}
	
	private void encryptPassword(User user) {
		String password=AuthenticationManager.encryptPassword(user.getPassword());
		user.setPassword(password);
	}
	
	@Override
	public RoleRepository getRepository() {
		return (RoleRepository)super.getRepository();
	}
	public void setRoleRepository(RoleRepository repository) {
		super.setRepository(repository);
	}
}
