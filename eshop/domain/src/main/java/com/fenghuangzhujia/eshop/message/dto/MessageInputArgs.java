package com.fenghuangzhujia.eshop.message.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MessageInputArgs extends DtoBaseModel {

	private String title;
	private String content;
	private String userid;
	
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
