package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.userGroup.UserGroupService;
import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupDto;
import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/userGroup")
public class UserGroupController extends PagingController<UserGroupDto, UserGroupInputArgs> {

	@Autowired
	private UserGroupService service;
	
	@Override
	public UserGroupService getService() {
		return service;
	}
}
