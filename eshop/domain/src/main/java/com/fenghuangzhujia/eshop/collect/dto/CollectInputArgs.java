package com.fenghuangzhujia.eshop.collect.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CollectInputArgs extends DtoBaseModel {

	private String name;
	private String url;
	private ResourceType type;
	private String sourceid;
	private String column;
	private String userid;
	private MultipartFile mainPicFile;
	
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public MultipartFile getMainPicFile() {
		return mainPicFile;
	}
	public void setMainPicFile(MultipartFile mainPicFile) {
		this.mainPicFile = mainPicFile;
	}
}
