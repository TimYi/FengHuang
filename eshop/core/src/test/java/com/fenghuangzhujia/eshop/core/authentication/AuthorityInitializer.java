package com.fenghuangzhujia.eshop.core.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.authentication.authority.concrete.ConcreteAuthorityService;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AuthorityInitializer {
	@Autowired
	ConcreteAuthorityService authorityService;
	@Autowired
	RoleService roleService;
	
	@Test
	public void init() {
	}
}
