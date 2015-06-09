package com.fenghuangzhujia.eshop.message.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MessageInputArgs extends DtoBaseModel {

	private String content;
	private String userid;
	
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
