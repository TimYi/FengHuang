package com.fenghuangzhujia.eshop.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.appoint.AppointService;
import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class AppointController {

	private AppointService appointService;	
	
	@RequestMapping(value="appoint",method=RequestMethod.POST)
	public String appoint(AppointDto appoint) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		appoint.setUserid(userid);
		AppointDto appointDto=appointService.add(appoint);
		return RequestResult.success(appointDto).toJson();
	}
	
	@RequestMapping(value="user/appoints",method=RequestMethod.GET)
	public String myAppoints(Integer page, Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<AppointDto> appoints=appointService.findByUserId(userid, page, size);
		return RequestResult.success(appoints).toJson();
	}
}
