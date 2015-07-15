package com.fenghuangzhujia.eshop.templateEngine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.templateEngine.TemplateDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;

public class Template extends UUIDBaseModel {

	/**模板名称*/
	private String name;
	/**模板效果图*/
	private MediaContent pic;
	/**解析之后的模板动态字段*/
	private Set<TemplateDefinition> definitions;
	/**模板内容，富文本字段*/
	private String content;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MediaContent getPic() {
		return pic;
	}
	public void setPic(MediaContent pic) {
		this.pic = pic;
	}
	public Set<TemplateDefinition> getDefinitions() {
		return definitions;
	}
	public void setDefinitions(Set<TemplateDefinition> definitions) {
		this.definitions = definitions;
	}
	@Type(type="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 从模板解析出Template实体
	 * 会将content和解析出的参数定义赋值给返回的Template实体
	 * @param content
	 * @return
	 */
	public static Template fromContent(String content) {
		List<String> tags=extractTags(content);
		Set<TemplateDefinition> definitions=resolveDefinitions(tags);
		validate(definitions);
		Template template=new Template();
		template.setContent(content);
		template.setDefinitions(definitions);
		return template;
	}
	
	/**
	 * 从模板中提取所有变量标签
	 * @param content
	 * @return
	 */
	private static List<String> extractTags(String content) {
		//TODO
		return null;
	}
	
	/**从标量标签中解析出字段定义*/
	private static TemplateDefinition resolveDefinition(String tag) {
		String[] values=tag.split(":");
		if(values.length<2) 
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "标签格式错误，至少要输入name:type");
		String name=values[0];
		String typeString=values[1];
		String description=values.length==3?values[2]:null;
		FieldType type=FieldType.fromString(typeString);
		TemplateDefinition definition=new TemplateDefinition();
		definition.setName(name);
		definition.setType(type);
		definition.setDescription(description);
		return definition;
	}
	
	/**
	 * 解析全部标签
	 * @param defs
	 * @return
	 */
	private static Set<TemplateDefinition> resolveDefinitions(List<String> tags) {
		Set<TemplateDefinition> definitions=new HashSet<>();
		for (String tag : tags) {
			TemplateDefinition definition=resolveDefinition(tag);
			definitions.add(definition);
		}
		return definitions;
	}
	
	/**
	 * 校验模板，防止标签名称重复或者为空
	 * @param definitions
	 * @return
	 */
	private static void validate(Set<TemplateDefinition> definitions) {
		Set<String> names=new HashSet<>();
		for (TemplateDefinition definition : definitions) {
			String name=definition.getName();
			if(StringUtils.isBlank(name))
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "标签名称不能为空");
			if(names.contains(name))
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "标签名称不能重复");
			names.add(name);
		}
	}
}
