package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.view.decorateTechnology.DecorateTechnologyService;
import com.fenghuangzhujia.eshop.view.decorateTechnology.dto.DecorateTechnologyDto;
import com.fenghuangzhujia.eshop.view.decorateTechnology.dto.DecorateTechnologyInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RequestMapping("admin/decorateTechnology")
@RestController
public class DecorateTechnologyController extends 
	SpecificationController<DecorateTechnologyDto, DecorateTechnologyInputArgs> {

	@Autowired
	private DecorateTechnologyService service;
	
	@Override
	public DecorateTechnologyService getService() {
		return service;
	}
}
