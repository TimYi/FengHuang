package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseumService;
import com.fenghuangzhujia.eshop.experienceMuseum.dto.ExperienceMuseumDto;
import com.fenghuangzhujia.eshop.experienceMuseum.dto.ExperienceMuseumInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminExperienceMuseumController")
@RequestMapping("experienceMuseum")
public class ExperienceMuseumController extends 
	SpecificationController<ExperienceMuseumDto, ExperienceMuseumInputArgs> {

	@Autowired
	private ExperienceMuseumService service;
	
	@Override
	public ExperienceMuseumService getService() {
		return service;
	}
}
