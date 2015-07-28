package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.foundation.core.rest.PagingController;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.dics.CategoryService;
import com.fenghuangzhujia.foundation.dics.dto.CategoryDto;

@RestController(value="adminCategoryController")
@RequestMapping("category")
public class CategoryController extends PagingController<CategoryDto, CategoryDto> {

	@Autowired
	private CategoryService service;
	
	@Override
	public CategoryService getService() {
		return service;
	}
	
	@RequestMapping(value="type/{type}",method=RequestMethod.GET)
	public String findSubCategoryItems(@PathVariable String type) {
		CategoryDto result=service.getByType(type);
		return RequestResult.success(result).toJson();
	}
}
