package com.fenghuangzhujia.eshop.core.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.authentication.role.RoleService;
import com.fenghuangzhujia.eshop.core.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AuthenticateTest {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//@Test
	public void regist() {
		//authenticationManager.regist("18612444099", "12345678");
	}
	
	//@Test
	public void addRole() {	
		
	}
	
	@Test
	public void addUser() {
		authenticationManager.regist("18612444099", "123456", null);
	}
	
	//@Test
	public void updateRole() {
		
	}

}
