package com.fenghuangzhujia.eshop.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.view.navigation.NavigationService;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationDto;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController(value="adminNavigationController")
@RequestMapping("navigation")
public class NavigationController extends PagingController<NavigationDto, NavigationInputArgs> {

	@Autowired
	private NavigationService service;
	
	@Override
	public NavigationService getService() {
		return service;
	}
	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String reOrder(@RequestBody Map<String, Integer> orders) {
		getService().reOrder(orders);
		return RequestResult.success("排序完成").toJson();
	}
}
