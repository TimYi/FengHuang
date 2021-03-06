package com.fenghuangzhujia.eshop.comment.dto;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CommentItemInputArgs extends DtoBaseModel {

	private String content;
	private String replyid;
	private String userid;
	private String ip;
	private String sourceid;
	private String url;
	private String column;
	private ResourceType type;
	
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
}
