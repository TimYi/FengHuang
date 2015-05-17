package com.fenghuangzhujia.eshop.core.user;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleRepository;
import com.fenghuangzhujia.foundation.core.service.AbstractPagingAndSortingService;

@Service
@Transactional
public class UserService extends AbstractPagingAndSortingService<User, String> {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthenticationManager manager;
	
	/**
	 * 根据用户名称获取用户实体
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	public User getByUsername(String username) throws UsernameNotFoundException {
		return manager.loadUserByUsername(username);
	}
	
	@Override
	public User findOne(String id) {
		User user=super.findOne(id);
		Hibernate.initialize(user.getRoles());
		return user;
	}
	
	@Override
	public User add(User t) {
		String username=t.getUsername();
		User user=getRepository().getByUsername(username);
		if(user!=null) {
			throw new RuntimeException("用户名重复！");
		}
		t=loadRoles(t, t.getRoleids());
		getRepository().save(t);
		return t;
	}
	
	@Override
	public User update(User t) {
		User user=getRepository().findOne(t.getId());
		user.setVerified(t.isVerified());
		//应该禁止非用户本人对用户名和密码的修改（只有创建时可以添加）
		//user.setUsername(t.getUsername());
		//user.setPassword(t.getPassword());
		user=loadRoles(user, t.getRoleids());
		return user;
	}
	
	private User loadRoles(User user, String[] roleids) {
		if(roleids==null) {
			return user;
		}
		Set<Role> roles=new HashSet<>();
		for (String roleid : roleids) {
			Role role=roleRepository.findOne(roleid);
			if(role!=null)
			roles.add(role);
		}
		user.setRoles(roles);
		return user;
	}
	
	@Autowired
	public void setUserRepository(UserRepository repository) {
		super.setRepository(repository);
	}
	@Override
	public UserRepository getRepository() {
		return (UserRepository)super.getRepository();
	}
	
	/**
	 * 判断用户是否有指定权限集合
	 * @param user
	 * @param authorities
	 * @return
	 */
	public boolean hasAuthority(User user, Set<String> authorities) {
		Set<String> authoritiesSet=new HashSet<>();
		for (Authority authority : user.getAuthorities()) {
			authoritiesSet.add(authority.getAuthority());
		}
		for (String au : authorities) {
			if(!authoritiesSet.contains(au))return false;
		}
		return true;
	}
	
	/**
	 * 判断用户是否有指定权限集合
	 * @param username
	 * @param authorities
	 * @return
	 */
	public boolean hasAuthority(String username, Set<String> authorities) {
		User user;
		try {
			user=getByUsername(username);
		} catch (UsernameNotFoundException e) {
			return false;
		}
		return hasAuthority(user, authorities);
	}
}



