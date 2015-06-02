package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleService;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/role")
public class RoleController extends PagingController<Role> {

	@Autowired
	private RoleService service;
	
	@Override
	protected RoleService getService() {
		return service;
	}
}
