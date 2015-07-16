package com.fenghuangzhujia.eshop.templateEngine.fragment.dto;

import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class VariableValueDto extends DtoBaseModel {

	/**变量名称*/
	private String name;
	/**变量类型*/
	private FieldType type;
	/**TEXT型变量值*/
	private String text;
	/**IMG型变量值*/
	private MediaContentDto pic;
	
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
	public MediaContentDto getPic() {
		return pic;
	}
	public void setPic(MediaContentDto pic) {
		this.pic = pic;
	}
}
