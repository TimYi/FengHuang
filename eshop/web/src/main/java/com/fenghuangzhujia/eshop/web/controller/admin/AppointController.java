package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.appoint.AppointService;
import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminAppointController")
@RequestMapping("admin/appoint")
public class AppointController extends SpecificationController<AppointDto> {

	@Autowired
	private AppointService service;
	
	@Override
	public AppointService getService() {
		return service;
	}
}
