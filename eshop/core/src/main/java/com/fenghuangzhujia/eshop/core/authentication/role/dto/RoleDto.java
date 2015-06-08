package com.fenghuangzhujia.eshop.core.authentication.role.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class RoleDto extends DtoBaseModel {

	private String name;
	private String description;
	private Set<UserDto> users;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<UserDto> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}
}
