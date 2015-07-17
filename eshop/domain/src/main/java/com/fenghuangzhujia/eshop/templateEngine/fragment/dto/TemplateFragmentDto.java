package com.fenghuangzhujia.eshop.templateEngine.fragment.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.templateEngine.template.dto.TemplateDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class TemplateFragmentDto extends DtoBaseModel {

	/**对应的模板*/
	private TemplateDto template;
	/**实际的变量*/
	private Set<VariableValueDto> values;
	/**计算生成的模板*/
	private String templateContent;
	
	public TemplateDto getTemplate() {
		return template;
	}
	public void setTemplate(TemplateDto template) {
		this.template = template;
	}
	public Set<VariableValueDto> getValues() {
		return values;
	}
	public void setValues(Set<VariableValueDto> values) {
		this.values = values;
	}
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateValue) {
		this.templateContent = templateValue;
	}
}
