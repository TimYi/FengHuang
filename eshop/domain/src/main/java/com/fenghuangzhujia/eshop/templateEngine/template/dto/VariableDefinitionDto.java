package com.fenghuangzhujia.eshop.templateEngine.template.dto;

import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class VariableDefinitionDto extends DtoBaseModel {

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
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
