package com.fenghuangzhujia.eshop.core.menu.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class MenuInputArgs extends DtoBaseModel {

	private String fatherid;
	private String name;
	private String description;
	private String typeid;
	/**方便记忆的标识码*/
	private String code;
	
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
