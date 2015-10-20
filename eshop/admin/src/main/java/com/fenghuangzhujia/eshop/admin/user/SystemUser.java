package com.fenghuangzhujia.eshop.admin.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_system_user")
public class SystemUser extends UUIDBaseModel {

	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**真实姓名*/
	private String realname;
	/**是否通过验证*/
	private boolean verified;
	
	@Column(nullable=false, unique=true)
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
