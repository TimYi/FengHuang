package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageService;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping("package")
public class PackageController extends SpecificationController<DecoratePackageDto,DecoratePackageInputArgs> {

	@Autowired
	private DecoratePackageService service;
	
	@Override
	public DecoratePackageService getService() {
		return service;
	}
}
