package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.brand.BrandService;
import com.fenghuangzhujia.eshop.core.brand.dto.BrandDto;
import com.fenghuangzhujia.eshop.core.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/brand")
public class BrandController extends PagingController<BrandDto,BrandInputArgs> {

	@Autowired
	private BrandService service;
	
	@Override
	protected BrandService getService() {
		return service;
	}
}
