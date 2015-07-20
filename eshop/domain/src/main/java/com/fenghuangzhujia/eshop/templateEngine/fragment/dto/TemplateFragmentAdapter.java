package com.fenghuangzhujia.eshop.templateEngine.fragment.dto;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.templateEngine.fragment.TemplateFragment;
import com.fenghuangzhujia.eshop.templateEngine.fragment.TemplateFragmentRepository;
import com.fenghuangzhujia.eshop.templateEngine.fragment.VariableValue;
import com.fenghuangzhujia.eshop.templateEngine.fragment.VariableValueRepository;
import com.fenghuangzhujia.eshop.templateEngine.template.Template;
import com.fenghuangzhujia.eshop.templateEngine.template.TemplateRepository;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.dto.adapter.SimpleDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class TemplateFragmentAdapter extends 
	SimpleDtoAdapter<TemplateFragment, TemplateFragmentDto, TemplateFragmentInputArgs> {
	
	@Autowired
	private MediaService mediaService;
	@Autowired
	private VariableValueRepository variableValueRepository;
	@Autowired
	private TemplateRepository templateRepository;
	@Autowired
	private TemplateFragmentRepository templateFragmentRepository;

	@Override
	public TemplateFragment convertToDo(TemplateFragmentInputArgs t) {
		TemplateFragment fragment=new TemplateFragment();
		
		//正确设置模板
		String templateId=t.getTemplateId();
		if(StringUtils.isBlank(templateId)) {
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "必须输入正确模板id");
		}
		Template template=templateRepository.findOne(templateId);
		fragment.setTemplate(template);
		
		//计算和验证参数
		Set<VariableValue> values=calculateValues(t.getValues());
		if(values==null) {
			return fragment;
		}
		validate(template, values);
		
		//保存fragment和参数
		fragment=templateFragmentRepository.save(fragment);
		Set<VariableValue> valueSet=new HashSet<>();
		for (VariableValue value : values) {
			value.setFragment(fragment);
			value=variableValueRepository.save(value);
			valueSet.add(value);
		}
		fragment.setValues(valueSet);
		//计算并缓存模板结果
		fragment.calculateTemplateValue();
		
		return fragment;
	}

	@Override
	public TemplateFragment update(TemplateFragmentInputArgs t,
			TemplateFragment d) {
		Set<VariableValue> oldValues=d.getValues();//记录旧参数
		
		//设置模板
		String templateId=t.getTemplateId();
		if(StringUtils.isBlank(templateId)) {
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "必须输入正确模板id");
		}
		Template template=templateRepository.findOne(templateId);
		d.setTemplate(template);
		
		//计算校验参数
		Set<VariableValue> values=calculateValues(t.getValues());
		if(values==null) {
			return d;
		}
		validate(template, values);
		
		//删除旧参数
		for (VariableValue value : oldValues) {
			variableValueRepository.delete(value);
		}
		
		//赋值新参数
		Set<VariableValue> valueSet=new HashSet<>();
		for (VariableValue value : values) {
			value.setFragment(d);
			value=variableValueRepository.save(value);
			valueSet.add(value);
		}
		d.setValues(valueSet);
		//计算并缓存模板结果
		d.calculateTemplateValue();
		
		return d;
	}

	@Override
	public TemplateFragmentDto convert(TemplateFragment source) {
		return BeanMapper.map(source, TemplateFragmentDto.class);
	}
	
	/**检验输入参数是否和模板匹配*/
	private void validate(Template template, Set<VariableValue> values) {		
		Set<VariableDefinition> definitions=template.getDefinitions();
		
		if(values==null) return;
		if(definitions==null) 
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "使用了模板中不需要的参数！");
		
		Set<String> names=new HashSet<>();
		for (VariableValue value : values) {
			String name=value.getName();
			if(StringUtils.isBlank(name))
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "变量名称不能为空");
			if(names.contains(name)) 
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "变量名称不能重复");
			names.add(name);
			VariableDefinition definition=getDefinition(name, definitions);
			validateValue(value, definition);
		}		
	}
	
	private VariableDefinition getDefinition(String name, Set<VariableDefinition> definitions) {
		for (VariableDefinition definition : definitions) {
			if(definition.getName().equals(name)) return definition;
		}
		return null;
	}
	
	/**验证同名变量和模板是否匹配*/
	private void validateValue(VariableValue value, VariableDefinition definition) {
		if(definition==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "使用了模板中不存在的变量名");
		if(value.getType()!=definition.getType())
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "变量类型和模板不匹配");
	}
	
	/**将输入参数转化为模板变量值*/
	private Set<VariableValue> calculateValues(Iterable<VariableValueInputArgs> args) {
		if(args==null)return null;
		Set<VariableValue> values=new HashSet<VariableValue>();
		for (VariableValueInputArgs arg : args) {
			VariableValue value=calculateValue(arg);
			values.add(value);
		}
		return values;
	}
	
	private VariableValue calculateValue(VariableValueInputArgs arg) {
		VariableValue value;
		if(StringUtils.isNotBlank(arg.getId())) {
			value=variableValueRepository.findOne(arg.getId());
			if(value==null)value=new VariableValue();
		} else {
			value=new VariableValue();
		}
		value.setName(arg.getName());
		value.setType(arg.getType());
		
		if(arg.getType()==FieldType.TEXT) {			
			value.setText(arg.getText());
		} 
		
		else {
			MultipartFile picFile=arg.getPicFile();
			if(picFile!=null) {
				try {
					MediaContent pic=value.getPic();
					pic=mediaService.update(pic, picFile);
					value.setPic(pic);
				} catch (Exception e) {
					LogUtils.errorLog(e);
				}
			}
		}
		return value;
	}

}
