package com.fenghuangzhujia.eshop.message;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.common.remind.impl.UnreadRemindEntity;
import com.fenghuangzhujia.eshop.core.user.User;

@Entity
@Table(name="fhzj_message")
public class Message extends UnreadRemindEntity {

	private String title;
	private String content;
	private User user;
	
	
	/**
	 * 留言标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 留言内容
	 * @return
	 */
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 留言所属用户
	 * @return
	 */
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
