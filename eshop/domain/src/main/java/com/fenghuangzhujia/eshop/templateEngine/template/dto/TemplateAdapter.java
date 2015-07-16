package com.fenghuangzhujia.eshop.templateEngine.template.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.templateEngine.template.TemplateResolver;
import com.fenghuangzhujia.eshop.templateEngine.template.Template;
import com.fenghuangzhujia.eshop.templateEngine.template.TemplateRepository;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinitionRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class TemplateAdapter extends AbstractDtoAdapter<Template, TemplateDto, TemplateInputArgs> {

	@Autowired
	private MediaService mediaService;
	@Autowired
	private TemplateRepository templateRepository;
	@Autowired
	private VariableDefinitionRepository variableDefinitionRepository;
	
	@Override
	public TemplateDto postConvert(Template d, TemplateDto t) {
		return t;
	}

	@Override
	public Template postConvertToDo(TemplateInputArgs i, Template d) {
		return postUpdate(i, d);
	}

	@Override
	public Template postUpdate(TemplateInputArgs i, Template d) {
		MultipartFile picFile=i.getPicFile();
		if(picFile!=null) {
			try {
				MediaContent pic=d.getPic();
				pic=mediaService.update(pic, picFile);
				d.setPic(pic);
			} catch (Exception e) {
				LogUtils.errorLog(e);
			}
		}
		String template=i.getContent();
		d=templateRepository.save(d);
		if(d.getDefinitions()!=null) {
			for (VariableDefinition definition : d.getDefinitions()) {
				variableDefinitionRepository.delete(definition);
			}
		}
		Set<VariableDefinition> definitions=TemplateResolver.fromTemplate(template);
		Set<VariableDefinition> definitionSet=new HashSet<>();
		for (VariableDefinition definition : definitions) {
			definition.setTemplate(d);
			definition=variableDefinitionRepository.save(definition);
			definitionSet.add(definition);
		}
		d.setDefinitions(definitionSet);
		return d;
	}
}
