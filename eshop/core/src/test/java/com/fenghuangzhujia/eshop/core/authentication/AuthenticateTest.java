package com.fenghuangzhujia.eshop.core.authentication;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityService;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleService;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AuthenticateTest {

	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	//@Test
	public void addRole() {
		Authority authority=new Authority();
		authority.setAuthority("ROLE_DESIGNER");
		authority=authorityService.add(authority);
		Role role=new Role();
		role.setName("Designer");
		Set<Authority> authorities=new HashSet<>();
		authorities.add(authority);
		role.setAuthorities(authorities);
		roleService.add(role);		
	}
	

	@Test
	public void allocateRole() {
		User user=userService.getByUsername("18612444099");
		String[] roleids={"404040e64cead202014cead205360001"};
		user.setRoleids(roleids);
		userService.update(user);
	}

}
