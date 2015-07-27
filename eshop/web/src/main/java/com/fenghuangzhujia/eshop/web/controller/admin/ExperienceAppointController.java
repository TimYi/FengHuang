package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.ExperienceAppointService;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.ExperienceAppoint.AppointStatus;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.dto.ExperienceAppointDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RequestMapping("admin/experienceAppoint")
@RestController
public class ExperienceAppointController {

	@Autowired
	private ExperienceAppointService service;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String page(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size,
			AppointStatus status) {
		PagedList<ExperienceAppointDto> result=service.findAppoints(page, size, status);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String info(@PathVariable String id) {
		ExperienceAppointDto result=service.findAppoint(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="{id}/calcel",method=RequestMethod.POST)
	public String cancel(@PathVariable String id) {
		service.calcelAppoint(id);
		return RequestResult.success("取消成功").toJson();
	}
	
	@RequestMapping(value="{id}/process",method=RequestMethod.POST)
	public String process(@PathVariable String id, String message,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date appointTime ) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		service.processAppoint(id, userId, message, appointTime);
		return RequestResult.success("处理完成").toJson();
	}
}
