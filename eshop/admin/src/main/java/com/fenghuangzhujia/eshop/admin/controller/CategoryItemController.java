package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.foundation.core.rest.PagingController;
import com.fenghuangzhujia.foundation.dics.CategoryItemService;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

@RestController(value="adminCategoryItemController")
@RequestMapping("categoryItem")
public class CategoryItemController extends PagingController<CategoryItemDto, CategoryItemDto> {

	@Autowired
	private CategoryItemService service;
	
	@Override
	public CategoryItemService getService() {
		return service;
	}
}
