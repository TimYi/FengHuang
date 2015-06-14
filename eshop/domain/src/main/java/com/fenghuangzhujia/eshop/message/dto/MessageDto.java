package com.fenghuangzhujia.eshop.message.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MessageDto extends DtoBaseModel {

	private String title;
	private String content;
	private UserDto user;
	private boolean readed;
	private Date createTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 为满足前端需求，临时写死的数据
	 * @return
	 */
	public String getSender() {
		return "系统管理员";
	}
}
