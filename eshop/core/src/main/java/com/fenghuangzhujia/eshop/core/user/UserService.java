package com.fenghuangzhujia.eshop.core.user;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.authority.AbstractAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityRepository;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleRepository;
import com.fenghuangzhujia.foundation.core.service.AbstractPagingService;

@Service
@Transactional
public class UserService extends AbstractPagingService<User, String> {
	
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
		t=loadAuthorities(t, t.getAuthorityids());
		getRepository().save(t);
		return t;
	}
	
	@Override
	public User update(User t) {
		User user=getRepository().findOne(t.getId());
		user.setVerified(t.isVerified());
		user=loadRoles(user, t.getRoleids());
		user=loadAuthorities(user, t.getAuthorityids());
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
	
	private User loadAuthorities(User user, String[] authorityids) {
		if(authorityids==null) {
			return user;
		}
		Set<AbstractAuthority> authorities=new HashSet<>();
		for (String authorityid : authorityids) {
			AbstractAuthority authority=authorityRepository.findOne(authorityid);
			if(authority!=null) {
				authorities.add(authority);
			}			
		}
		user.setAuthorities(authorities);
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
}



