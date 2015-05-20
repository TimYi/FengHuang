package com.fenghuangzhujia.foundation.core.dto;

import java.util.Date;

import com.fenghuangzhujia.foundation.core.entity.Identified;

public class DtoBaseModel implements Identified<String> {
	private String id;
	private Date createTime;
	private Date updateTime;
	
	public DtoBaseModel() {
		createTime=new Date();
		updateTime=new Date();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
