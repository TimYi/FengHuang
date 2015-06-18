package com.fenghuangzhujia.eshop.worker.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class WorkerInputArgs extends DtoBaseModel {

	private String username;
	private String password;
	/**工人姓名*/
	private String name;
	/**工种，字典类型worker*/
	private String typeId;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
