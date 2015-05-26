package com.fenghuangzhujia.eshop.user.message;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_message")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="comment_type")
@DiscriminatorValue(value="BASIC")
public class Message extends UUIDBaseModel {
	private User user;
	private boolean niming;
	private String mobile;
	private String email;
	private String qqnum;
	private String message;
	private String type;
	
	/**
	 * 用户可能为空，为匿名留言
	 * @return
	 */
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 是否为匿名
	 * @return
	 */
	public boolean isNiming() {
		return niming;
	}
	public void setNiming(boolean niming) {
		this.niming = niming;
	}
	
	/**
	 * 非匿名留言，留下手机号等信息
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQqnum() {
		return qqnum;
	}
	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name="comment_type",insertable=false,updatable=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
