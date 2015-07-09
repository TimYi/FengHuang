package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.meteriaManage.brand.BrandService;
import com.fenghuangzhujia.eshop.meteriaManage.brand.dto.BrandDto;
import com.fenghuangzhujia.eshop.meteriaManage.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminBrandController")
@RequestMapping("admin/brand")
public class BrandController extends SpecificationController<BrandDto, BrandInputArgs> {

	@Autowired
	private BrandService service;
	
	@Override
	public BrandService getService() {
		return service;
	}
}
