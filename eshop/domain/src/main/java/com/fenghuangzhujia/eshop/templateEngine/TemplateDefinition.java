package com.fenghuangzhujia.eshop.templateEngine;

import org.apache.commons.lang3.StringUtils;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

public class TemplateDefinition extends UUIDBaseModel {

	/**字段名称*/
	private String name;
	/**字段类型*/
	private FieldType type;
	/**字段描述*/
	private String description;
	
	
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
	public String getDescription() {
		if(StringUtils.isBlank(description)) return name;
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public static enum FieldType {
		TEXT,IMG;
		
		public static FieldType fromString(String type) {
			type=type.toUpperCase();
			return FieldType.valueOf(type);
		}
	}
}
