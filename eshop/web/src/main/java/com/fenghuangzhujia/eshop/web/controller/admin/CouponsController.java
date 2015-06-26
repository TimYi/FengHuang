package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.coupons.CouponsService;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsDto;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminCouponsController")
@RequestMapping("admin/coupons")
public class CouponsController extends SpecificationController<CouponsDto, CouponsInputArgs> {

	@Autowired
	private CouponsService service;
	
	@Override
	public CouponsService getService() {
		return service;
	}
}
