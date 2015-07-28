package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.coupons.CouponsService;
import com.fenghuangzhujia.eshop.core.coupons.dto.CouponsDto;
import com.fenghuangzhujia.eshop.core.coupons.dto.CouponsInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminCouponsController")
@RequestMapping("coupons")
public class CouponsController extends SpecificationController<CouponsDto, CouponsInputArgs> {

	@Autowired
	private CouponsService service;
	
	@Override
	public CouponsService getService() {
		return service;
	}
}
