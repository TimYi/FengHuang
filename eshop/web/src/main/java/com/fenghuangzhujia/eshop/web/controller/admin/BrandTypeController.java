package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.brand.BrandTypeService;
import com.fenghuangzhujia.eshop.core.brand.dto.BrandTypeDto;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/brandType")
public class BrandTypeController extends PagingController<BrandTypeDto> {

	@Autowired
	private BrandTypeService service;
	
	@Override
	public BrandTypeService getService() {
		return service;
	}
}
