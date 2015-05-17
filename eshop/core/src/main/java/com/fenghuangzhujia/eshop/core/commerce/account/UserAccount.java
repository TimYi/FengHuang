package com.fenghuangzhujia.eshop.core.commerce.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fenghuangzhujia.eshop.core.user.User;

/**
 * 个人账户
 * @author pc
 *
 */
//@Entity
@DiscriminatorValue(value="USER")
public class UserAccount extends Account {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
