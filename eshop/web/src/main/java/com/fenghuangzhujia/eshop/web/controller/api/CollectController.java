package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.eshop.collect.CollectService;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class CollectController {
	
	@Autowired
	private CollectService collectService;

	@RequestMapping(value="collect",method=RequestMethod.POST)
	public String collect(@RequestParam String sourceid, @RequestParam ResourceType type, String url) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		collectService.add(userid, sourceid, type, url);
		return RequestResult.success("保存成功").toJson();
	}
}
