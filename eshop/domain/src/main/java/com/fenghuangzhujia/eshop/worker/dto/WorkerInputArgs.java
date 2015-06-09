package com.fenghuangzhujia.eshop.worker.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class WorkerInputArgs extends DtoBaseModel {

	private String username;
	private String password;
	
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
}
