package com.fenghuangzhujia.eshop.core.authentication.role.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class RoleInputArgs extends DtoBaseModel {

	private String name;
	private String description;
	private String[] userids;
	
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
	public String[] getUserids() {
		return userids;
	}
	public void setUserids(String[] userids) {
		this.userids = userids;
	}
}
