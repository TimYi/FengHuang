package com.fenghuangzhujia.eshop.admin.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.security.rest.Token;

@Entity
@Table(name="fhzj_system_token")
public class SystemToken extends UUIDBaseModel implements Token {

	private String token;
	private SystemUser user;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@OneToOne(optional=false)
	@JoinColumn(unique=true)
	public SystemUser getUser() {
		return user;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
}
