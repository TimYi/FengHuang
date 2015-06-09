package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.menu.MenuService;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuDto;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/menu")
public class MenuController extends PagingController<MenuDto,MenuInputArgs> {
	
	@Autowired
	private MenuService service;

	@Override
	protected MenuService getService() {
		return service;
	}
}
