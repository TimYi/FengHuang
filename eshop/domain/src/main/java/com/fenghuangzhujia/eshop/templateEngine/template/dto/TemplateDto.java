package com.fenghuangzhujia.eshop.templateEngine.template.dto;

import java.util.Set;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class TemplateDto extends DtoBaseModel {

	/**模板名称*/
	private String name;
	/**模板分类，是一个标记字段*/
	private String type;
	/**模板效果图*/
	private MediaContentDto pic;
	/**解析之后的模板动态字段*/
	private Set<VariableDefinitionDto> definitions;
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
	public MediaContentDto getPic() {
		return pic;
	}
	public void setPic(MediaContentDto pic) {
		this.pic = pic;
	}
	public Set<VariableDefinitionDto> getDefinitions() {
		return definitions;
	}
	public void setDefinitions(Set<VariableDefinitionDto> definitions) {
		this.definitions = definitions;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
