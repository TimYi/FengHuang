package com.fenghuangzhujia.eshop.comment.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CommentItemDto extends DtoBaseModel {

	private String content;
	private CommentItemDto reply;
	private UserDto user;
	private String ip;
	private String sourceid;
	private String url;
	private Date createTime;
	private String column;
	private ResourceType type;
	private Integer replyNumber; //回复数量，需要特殊处理
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public CommentItemDto getReply() {
		return reply;
	}
	public void setReply(CommentItemDto reply) {
		this.reply = reply;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public Integer getReplyNumber() {
		return replyNumber;
	}
	public void setReplyNumber(Integer replyNumber) {
		this.replyNumber = replyNumber;
	}
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
}
