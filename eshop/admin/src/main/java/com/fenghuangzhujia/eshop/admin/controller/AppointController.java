package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.appoint.AppointService;
import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.eshop.appoint.dto.AppointInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminAppointController")
@RequestMapping("appoint")
public class AppointController extends SpecificationController<AppointDto,AppointInputArgs> {

	@Autowired
	private AppointService service;
	
	@Override
	public AppointService getService() {
		return service;
	}
}
