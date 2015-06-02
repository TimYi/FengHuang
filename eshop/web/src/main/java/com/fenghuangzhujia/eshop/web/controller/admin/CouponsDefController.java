package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.coupons.CouponsDefService;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsDefDto;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/couponsDef")
public class CouponsDefController extends PagingController<CouponsDefDto> {

	@Autowired
	private CouponsDefService service;
	
	@Override
	public CouponsDefService getService() {
		return service;
	}
}
