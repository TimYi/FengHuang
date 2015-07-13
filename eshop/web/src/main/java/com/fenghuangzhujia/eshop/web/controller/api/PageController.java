package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.view.navigation.NavigationService;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationDto;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class PageController {
	
	@Autowired
	private NavigationService navigationService;

	@RequestMapping(value="navigations",method=RequestMethod.GET)
	public String navagations() {
		Iterable<NavigationDto> result=navigationService.findAll();
		return RequestResult.success(result).toJson();
	}
}
