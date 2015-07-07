package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.role.RoleService;
import com.fenghuangzhujia.eshop.core.authentication.role.dto.RoleDto;
import com.fenghuangzhujia.eshop.core.authentication.role.dto.RoleInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
@RequestMapping("admin/role")
public class RoleController extends PagingController<RoleDto,RoleInputArgs> {

	@Autowired
	private RoleService service;
	
	@Override
	protected RoleService getService() {
		return service;
	}
	
	@RequestMapping(value="addRoleUser",method=RequestMethod.POST)
	public String addRoleUser(String username, String password, String roleName) {
		getService().addRoleUser(username, password, roleName);
		return RequestResult.success("添加成功").toJson();
	}
	
	/**
	 * 从指定角色中删除用户，但是不删除用户本身。
	 * @param id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="{id}/user/{userId}",method=RequestMethod.DELETE)
	public String removeUser(@PathVariable String id, @PathVariable String userId) {
		getService().removeUser(id, userId);
		return RequestResult.success("删除成功").toJson();
	}
}
