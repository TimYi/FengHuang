package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.experience.ExperienceMuseumService;
import com.fenghuangzhujia.eshop.experience.dto.ExperienceMuseumDto;
import com.fenghuangzhujia.eshop.experience.dto.ExperienceMuseumInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminExperienceMuseumController")
@RequestMapping("admin/experienceMuseum")
public class ExperienceMuseumController extends 
	SpecificationController<ExperienceMuseumDto, ExperienceMuseumInputArgs> {

	private ExperienceMuseumService service;
	
	@Override
	public ExperienceMuseumService getService() {
		return service;
	}
}
