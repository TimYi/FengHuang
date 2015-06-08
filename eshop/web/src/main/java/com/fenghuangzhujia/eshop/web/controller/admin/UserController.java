package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.user.UserService;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminUserController")
@RequestMapping("admin/user")
public class UserController extends SpecificationController<UserDto,UserInputArgs> {

	@Autowired
	private UserService service;
	
	@Override
	public UserService getService() {
		return service;
	}
}
