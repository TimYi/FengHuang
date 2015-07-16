package com.fenghuangzhujia.eshop.templateEngine.template;

import java.util.List;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface TemplateRepository extends SpecificationRepository<Template, String> {

	Template getByName(String name);
	
	List<Template> findByType(String type);
}
