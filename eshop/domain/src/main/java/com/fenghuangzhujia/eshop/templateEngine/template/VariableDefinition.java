package com.fenghuangzhujia.eshop.templateEngine.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_template_variable")
public class VariableDefinition extends UUIDBaseModel {

	/**字段名称*/
	private String name;
	/**字段类型*/
	private FieldType type;
	/**字段描述*/
	private String description;
	/**所属模板*/
	private Template template;
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false)
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
	@ManyToOne(optional=false)
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}


	public static enum FieldType {
		TEXT,IMG;
		
		public static FieldType fromString(String type) {
			type=type.toUpperCase();
			return FieldType.valueOf(type);
		}
	}
}
