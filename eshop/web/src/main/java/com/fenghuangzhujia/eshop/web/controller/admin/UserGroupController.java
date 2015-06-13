package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.userGroup.UserGroupService;
import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupDto;
import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
@RequestMapping("admin/userGroup")
public class UserGroupController extends PagingController<UserGroupDto, UserGroupInputArgs> {

	@Autowired
	private UserGroupService service;
	
	@Override
	public UserGroupService getService() {
		return service;
	}
	
	@RequestMapping(value="/addAll",method=RequestMethod.POST)
	public String addAll(@RequestBody List<UserGroupInputArgs> list) {
		List<UserGroupDto> result=getService().addAll(list);
		return RequestResult.success(result).toJson();
	}
	
	public static class UserGroupList {
		private List<UserGroupInputArgs> list;

		public List<UserGroupInputArgs> getList() {
			return list;
		}

		public void setList(List<UserGroupInputArgs> list) {
			this.list = list;
		}
	}
}
