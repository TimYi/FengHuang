package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.coupons.CouponsService;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsDto;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class CouponsController {

	@Autowired
	private CouponsService couponsService;
	
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
}
