package com.fenghuangzhujia.eshop.templateEngine.template.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class TemplateInputArgs extends DtoBaseModel {

	/**模板名称*/
	private String name;
	/**模板分类，是一个标记字段*/
	private String type;
	/**模板效果图*/
	private MultipartFile picFile;
	/**模板内容，富文本字段*/
	private String content;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MultipartFile getPicFile() {
		return picFile;
	}
	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
