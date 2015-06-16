package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.appoint.AppointService;
import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.eshop.appoint.dto.AppointInputArgs;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppointService;
import com.fenghuangzhujia.eshop.prudoct.appoint.dto.PackageAppointDto;
import com.fenghuangzhujia.eshop.prudoct.appoint.dto.PackageAppointInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class AppointController {

	@Autowired
	private AppointService appointService;	
	@Autowired
	private PackageAppointService packageAppointService;
	
	@RequestMapping(value="user/appoint",method=RequestMethod.POST)
	public String appoint(AppointInputArgs args) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		args.setUserId(userid);
		AppointDto result=appointService.appointByUser(args);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/packageAppoint",method=RequestMethod.POST)
	public String appointPackage(PackageAppointInputArgs args) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		args.setUserId(userid);
		PackageAppointDto result=packageAppointService.appointByUser(args);
		return RequestResult.success(result).toJson();
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
