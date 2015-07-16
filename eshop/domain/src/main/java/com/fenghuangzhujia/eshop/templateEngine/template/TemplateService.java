package com.fenghuangzhujia.eshop.templateEngine.template;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.templateEngine.template.dto.TemplateDto;
import com.fenghuangzhujia.eshop.templateEngine.template.dto.TemplateInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class TemplateService extends DtoSpecificationService<Template, TemplateDto, TemplateInputArgs, String> {

	@Override
	public TemplateRepository getRepository() {
		return (TemplateRepository)super.getRepository();
	}
	
	public TemplateDto getByName(String name) {
		Template template=getRepository().getByName(name);
		return adapter.convertToDetailedDto(template);
	}
	
	public List<TemplateDto> findByType(String type) {
		List<Template> templates=getRepository().findByType(type);
		return adapter.convertDoList(templates);
	}
}
