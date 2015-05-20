package com.fenghuangzhujia.eshop.user.comment.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CommentDto extends DtoBaseModel {	
	private String userid;
	private String message;
	private String type;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
