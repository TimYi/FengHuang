package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.couponsDef.CouponsAllocater;
import com.fenghuangzhujia.eshop.core.couponsDef.CouponsDefService;
import com.fenghuangzhujia.eshop.core.couponsDef.dto.CouponsDefDto;
import com.fenghuangzhujia.eshop.core.couponsDef.dto.CouponsDefInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping("admin/couponsDef")
public class CouponsAllocaterController extends SpecificationController<CouponsDefDto, CouponsDefInputArgs> {

	@Autowired
	private CouponsDefService service;
	
	@Override
	public CouponsDefService getService() {
		return service;
	}
	
	@RequestMapping(value="events",method=RequestMethod.GET)
	public String getEventList() {
		return RequestResult.success(CouponsAllocater.COUPONS_EVENTS).toJson();
	}
	
	@RequestMapping(value="event/{event}",method=RequestMethod.GET)
	public String getByEvent(@PathVariable String event) {
		CouponsDefDto result=getService().findByEvent(event);
		return RequestResult.success(result).toJson();
	}
}
