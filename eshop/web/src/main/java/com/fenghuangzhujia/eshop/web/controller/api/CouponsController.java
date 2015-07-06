package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.commerce.coupons.CouponsService;
import com.fenghuangzhujia.eshop.core.commerce.coupons.dto.CouponsDto;
import com.fenghuangzhujia.eshop.core.commerce.couponsDef.CouponsAllocater;
import com.fenghuangzhujia.eshop.core.commerce.couponsDef.CouponsDefService;
import com.fenghuangzhujia.eshop.core.commerce.couponsDef.dto.CouponsDefDto;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class CouponsController {

	@Autowired
	private CouponsService couponsService;
	@Autowired
	private CouponsDefService couponsDefService;
	@Autowired
	private CouponsAllocater couponsAllocater;
	
	@RequestMapping(value="user/coupons",method=RequestMethod.GET)
	public String coupons(@RequestParam(defaultValue="true") boolean notUsed) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		List<CouponsDto> result;
		if(notUsed) {
			result=couponsService.findUnUsedCoupons(userid);
		} else {
			result=couponsService.findUserCoupons(userid);
		}
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="coupons/scramble",method=RequestMethod.GET)
	public String scramble() {
		CouponsDefDto def=couponsDefService.findByEvent("qg");
		return RequestResult.success(def).toJson();
	}
	
	@RequestMapping(value="coupons/scramble",method=RequestMethod.POST)
	public String doScramble(){
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		boolean success=couponsAllocater.scramble(userId);
		if(success)return RequestResult.success(true).toJson();
		return RequestResult.success(false).toJson();
	}
}
