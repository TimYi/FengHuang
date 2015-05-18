package com.fenghuangzhujia.eshop.core.authentication.role;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.authority.AbstractAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityRepository;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.service.AbstractPagingAndSortingService;

@Service
@Transactional
public class RoleService extends AbstractPagingAndSortingService<Role, String> {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Role add(Role t) {
		t=loadAuthorities(t, t.getAuthorityids());
		t=loadUsers(t, t.getUserids());
		getRepository().save(t);
		return t;
	}
	
	public Role getByName(String name) {
		return getRepository().getByName(name);
	}
	
	/**
	 * 加载Users
	 */
	@Override
	public Role findOne(String id) {
		Role role=super.findOne(id);
		if(role==null) return null;
		Hibernate.initialize(role.getUsers());
		Hibernate.initialize(role.getAuthorities());
		return role;
	}
	
	@Override
	public Role update(Role t) {
		Role role=getRepository().findOne(t.getId());
		role.setName(t.getName());
		role.setDescription(t.getDescription());
		role=loadAuthorities(role, t.getAuthorityids());
		role=loadUsers(role, t.getUserids());
		getRepository().save(role);
		return role;
	}
	
	private Role loadAuthorities(Role role, String[] authorityids) {
		if(authorityids==null) {
			role.setAuthorities(null);
			return role;
		}
		Set<AbstractAuthority> authorities=new HashSet<>();
		for (String authorityid : authorityids) {
			AbstractAuthority authority=authorityRepository.findOne(authorityid);
			authorities.add(authority);
		}
		role.setAuthorities(authorities);
		return role;
	}
	
	private Role loadUsers(Role role, String[] userids) {
		if(userids==null){
			role.setUsers(null);
			return role;
		}
		Set<User> users=new HashSet<>();
		for (String userid : userids) {
			User user=userRepository.findOne(userid);
			users.add(user);
		}
		role.setUsers(users);
		return role;
	}
	
	@Override
	public RoleRepository getRepository() {
		return (RoleRepository)super.getRepository();
	}
	public void setRoleRepository(RoleRepository repository) {
		super.setRepository(repository);
	}
}
