package com.fenghuangzhujia.eshop.core.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.authentication.authority.concrete.ConcreteAuthorityService;
import com.fenghuangzhujia.eshop.core.authentication.authority.concrete.ConcreteAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.opration.OperationAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.opration.OperationAuthorityService;
import com.fenghuangzhujia.eshop.core.authentication.authority.resource.ResourceAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.resource.ResourceAuthorityService;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleService;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AuthenticateTest {

	@Autowired
	private ConcreteAuthorityService authorityService;
	@Autowired
	private ResourceAuthorityService resourceAuthorityService;
	@Autowired
	private OperationAuthorityService operationAuthorityService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	//@Autowired
	//private AuthenticationManager authenticationManager;
	
	//@Test
	public void regist() {
		//authenticationManager.regist("18612444099", "12345678");
	}
	
	//@Test
	public void addRole() {	
		Role role=new Role();
		role.setName("test");
		String[] userids={"404040e64d6a23b1014d6a23b64d0001"};
		role.setUserids(userids);
		roleService.add(role);
	}
	
	//@Test
	public void updateRole() {
		Role role=new Role();
		role.setId("404040e64d6a2358014d6a235d320000");
		role.setName("test");
		String[] userids={"404040e64d6a23b1014d6a23b64d0001"};
		role.setUserids(userids);
		roleService.update(role);
	}
	

	@Test
	public void allocateRole() {
		User user=userService.getByUsername("18612444099");
		String[] roleids={"404040e64d6a2358014d6a235d320000"};
		user.setRoleids(roleids);
		String[] authorityids={"404040e64d6a2261014d6a22b4650000"};
		user.setAuthorityids(authorityids);
		userService.update(user);
	}
	
	//@Test
	public void addAuthority() {
		ResourceAuthority resourceAuthority=new ResourceAuthority();
		resourceAuthority.setName("test");
		resourceAuthority=resourceAuthorityService.add(resourceAuthority);
		OperationAuthority operationAuthority=new OperationAuthority();
		operationAuthority.setResource(resourceAuthority);
		operationAuthority.setName("test");
		operationAuthority=operationAuthorityService.add(operationAuthority);
		ConcreteAuthority concreteAuthority=new ConcreteAuthority();
		concreteAuthority.setOperation(operationAuthority);
		concreteAuthority.setResourceid("test");
		authorityService.add(concreteAuthority);
	}

}
