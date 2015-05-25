package com.fenghuangzhujia.eshop.core.authentication.token;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_user_token")
public class UserToken extends UUIDBaseModel {
	
	private String token;
	private User user;
	
	/**
	 * 注册，每次登陆刷新此token
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the user
	 */
	@OneToOne(optional=false)
	@JoinColumn(name="userinfoid")
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
