package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fenghuangzhujia.eshop.admin.user.SystemUser;
import com.fenghuangzhujia.eshop.admin.user.SystemUserService;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@Controller
@RequestMapping("systemUser")
public class SystemUserController extends PagingController<SystemUser, SystemUser> {

	
	@Autowired
	private SystemUserService service;
	@Override
	protected SystemUserService getService() {
		return service;
	}
}
