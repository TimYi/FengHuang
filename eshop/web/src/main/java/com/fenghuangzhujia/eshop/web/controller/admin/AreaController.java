package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.area.AreaService;
import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.area.dto.AreaInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController(value="adminAreaController")
@RequestMapping("admin/area")
public class AreaController extends PagingController<AreaDto,AreaInputArgs> {

	@Autowired
	private AreaService service;
	
	@Override
	public AreaService getService() {
		return service;
	}
}
