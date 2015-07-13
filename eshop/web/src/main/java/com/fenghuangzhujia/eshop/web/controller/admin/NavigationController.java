package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.view.navigation.NavigationService;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationDto;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController(value="adminNavigationController")
@RequestMapping("admin/navigation")
public class NavigationController extends PagingController<NavigationDto, NavigationInputArgs> {

	@Autowired
	private NavigationService service;
	
	@Override
	public NavigationService getService() {
		return service;
	}
}
