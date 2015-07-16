package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.templateEngine.template.TemplateService;
import com.fenghuangzhujia.eshop.templateEngine.template.dto.TemplateDto;
import com.fenghuangzhujia.eshop.templateEngine.template.dto.TemplateInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminTemplateController")
@RequestMapping("admin/template")
public class TemplateController extends SpecificationController<TemplateDto, TemplateInputArgs> {

	@Autowired
	private TemplateService service;
	
	@Override
	public TemplateService getService() {
		return service;
	}
	
	@RequestMapping(value="byname/{name}",method=RequestMethod.GET)
	public String getByName(@PathVariable String name) {
		TemplateDto result=getService().getByName(name);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="bytype/{type}",method=RequestMethod.GET)
	public String getByType(@PathVariable String type) {
		List<TemplateDto> templates=getService().findByType(type);
		PagedList<TemplateDto> result=new PagedList<>(templates);
		return RequestResult.success(result).toJson();
	}
}
