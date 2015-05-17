package com.fenghuangzhujia.eshop.core.authentication;

import static com.fenghuangzhujia.eshop.core.base.AuthorityValues.*;

import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityService;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AuthorityInitializer {
	@Autowired
	AuthorityService authorityService;
	@Autowired
	RoleService roleService;
	
	@Test
	public void init() {
		Authority authority;
		Role role;
		String[] authorities={DESIGNER,ADMIN,ENTER};
		for (String au : authorities) {
			authority=new Authority();
			authority.setAuthority(au);
			authorityService.add(authority);
			role=new Role();
			role.setName(StringUtils.removeStart(au, PREFIX));
			HashSet<Authority> roleAuthorities=new HashSet<>();
			roleAuthorities.add(authority);
			role.setAuthorities(roleAuthorities);
			roleService.add(role);
		}
	}
}
