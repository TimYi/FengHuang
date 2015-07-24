package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseumService;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.ExperienceAppointService;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.dto.ExperienceAppointDto;
import com.fenghuangzhujia.eshop.experienceMuseum.dto.ExperienceMuseumDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class ExperienceMuseumController {

	@Autowired
	private ExperienceAppointService experienceAppointService;
	@Autowired
	private ExperienceMuseumService experienceMuseumService;
	
	@RequestMapping(value="museum/{id}/appoint",method=RequestMethod.POST)
	public String appoint(@PathVariable String id, String realName, String mobile, String validater) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		ExperienceAppointDto result=experienceAppointService.appoint(userId, id, realName, mobile, validater);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="museum/{id}",method=RequestMethod.GET)
	public String museum(@PathVariable String id) {
		ExperienceMuseumDto result=experienceMuseumService.findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="museums",method=RequestMethod.GET)
	public String museums(@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="8") Integer size) {
		PagedList<ExperienceMuseumDto> result=experienceMuseumService.findPage(page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/museumAppoints",method=RequestMethod.GET)
	public String myAppoints(@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="8") Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		PagedList<ExperienceAppointDto> result=experienceAppointService.findUserAppoints(userId, page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/museumAppoint/{id}",method=RequestMethod.GET)
	public String myAppoints(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		ExperienceAppointDto result=experienceAppointService.getUserAppoint(id, userId);
		return RequestResult.success(result).toJson();
	}
}
