package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.foundation.area.AreaService;
import com.fenghuangzhujia.foundation.area.dto.AreaDto;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/area")
public class AreaController extends PagingController<AreaDto> {

	@Autowired
	private AreaService service;
	
	@Override
	public AreaService getService() {
		return service;
	}
}
