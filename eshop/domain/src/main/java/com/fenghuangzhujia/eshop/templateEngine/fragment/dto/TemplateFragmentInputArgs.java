package com.fenghuangzhujia.eshop.templateEngine.fragment.dto;

import java.util.Set;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class TemplateFragmentInputArgs extends DtoBaseModel {

	/**对应的模板id*/
	private String templateId;
	/**实际的变量*/
	private Set<VariableValueInputArgs> values;
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public Set<VariableValueInputArgs> getValues() {
		return values;
	}
	public void setValues(Set<VariableValueInputArgs> values) {
		this.values = values;
	}
}
