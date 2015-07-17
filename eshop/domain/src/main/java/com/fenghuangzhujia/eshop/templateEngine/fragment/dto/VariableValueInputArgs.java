package com.fenghuangzhujia.eshop.templateEngine.fragment.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class VariableValueInputArgs extends DtoBaseModel {

	/**变量名称*/
	private String name;
	/**变量类型*/
	private FieldType type;
	/**TEXT型变量值*/
	private String text;
	/**IMG型变量值*/
	private MultipartFile picFile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FieldType getType() {
		return type;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MultipartFile getPicFile() {
		return picFile;
	}
	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
	}
}
