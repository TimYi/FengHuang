package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.appoint.AppointService;
import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class AppointController {

	@Autowired
	private AppointService appointService;	
	
	@RequestMapping(value="user/appoint",method=RequestMethod.POST)
	public String appoint(AppointDto appoint) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		appoint.setUserid(userid);
		AppointDto appointDto=appointService.add(appoint);
		return RequestResult.success(appointDto).toJson();
	}
	
	@RequestMapping(value="user/appoints",method=RequestMethod.GET)
	public String myAppoints(@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="8") Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<AppointDto> appoints=appointService.findByUserId(userid, page, size);
		return RequestResult.success(appoints).toJson();
	}
	
	@RequestMapping(value="user/appoint/{id}",method=RequestMethod.GET)
	public String myAppoint(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		AppointDto appoint=appointService.getUserAppoint(userid, id);
		return RequestResult.success(appoint).toJson();
	}
}
