package com.fenghuangzhujia.eshop.templateEngine.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.templateEngine.fragment.VariableValue;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

public class TemplateResolver {

	/**
	 * 从模板解析出全部参数定义
	 * @param template
	 * @return
	 */
	public static Set<VariableDefinition> fromTemplate(String template) {
		List<String> tags=extractTags(template);
		Set<VariableDefinition> definitions=resolveDefinitions(tags);
		validate(definitions);
		return definitions;
	}
	
	/**从标量标签中解析出字段定义*/
	public static VariableDefinition resolveDefinition(String tag) {
		String[] values=tag.split(":");
		if(values.length<2) 
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "标签格式错误，至少要输入name:type");
		String name=values[0].trim();
		String typeString=values[1].trim();
		String description=values.length==3?values[2].trim():null;
		FieldType type=FieldType.fromString(typeString);
		VariableDefinition definition=new VariableDefinition();
		definition.setName(name);
		definition.setType(type);
		definition.setDescription(description);
		return definition;
	}
	
	public static String calculateTemplate(String template, Set<VariableValue> values) {
		TemplateReader reader=new DefaultTemplateReader(template);
		while (reader.next()) {
			String name=reader.getVariableName();
			String value=getVariableValue(name, values);
			reader.injectCurrentVariable(value);			
		}
		return reader.getTemplate();
	}
	
	private static String getVariableValue(String name, Set<VariableValue> values) {
		for (VariableValue value : values) {
			if(value.getName().equals(name)) {
				return value.toString();
			}
		}
		return null;
	}
	
	/**
	 * 从模板中提取所有变量标签
	 * @param template
	 * @return
	 */
	private static List<String> extractTags(String template) {
		TemplateReader reader=new DefaultTemplateReader(template);
		List<String> tags=new ArrayList<String>();
		while (reader.next()) {
			tags.add(reader.getVariable());
		}
		return tags;
	}
	
	/**
	 * 解析全部标签
	 * @param defs
	 * @return
	 */
	private static Set<VariableDefinition> resolveDefinitions(List<String> tags) {
		Set<VariableDefinition> definitions=new HashSet<>();
		for (String tag : tags) {
			VariableDefinition definition=resolveDefinition(tag);
			definitions.add(definition);
		}
		return definitions;
	}
	
	/**
	 * 校验模板，防止标签名称重复或者为空
	 * @param definitions
	 * @return
	 */
	private static void validate(Set<VariableDefinition> definitions) {
		Set<String> names=new HashSet<>();
		for (VariableDefinition definition : definitions) {
			String name=definition.getName();
			if(StringUtils.isBlank(name))
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "标签名称不能为空");
			if(names.contains(name))
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "标签名称不能重复");
			names.add(name);
		}
	}
}
