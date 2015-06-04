package com.fenghuangzhujia.eshop.comment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CommentItemDto extends DtoBaseModel {

	private String content;
	@JsonIgnore
	private String replyid;
	private CommentItemDto reply;
	@JsonIgnore
	private String userid;
	private UserDto user;
	private String ip;
	private String sourceid;
	private String url;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}
	public CommentItemDto getReply() {
		return reply;
	}
	public void setReply(CommentItemDto reply) {
		this.reply = reply;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
