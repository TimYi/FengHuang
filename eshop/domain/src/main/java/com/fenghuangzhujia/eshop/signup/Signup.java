package com.fenghuangzhujia.eshop.signup;

import javax.persistence.Entity;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
public class Signup extends UUIDBaseModel {

	private String telephone;
	private String name;
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
