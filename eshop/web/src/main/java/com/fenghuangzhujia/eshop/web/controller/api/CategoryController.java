package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.dics.CategoryItemService;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryItemService categoryItemService;
	
	@RequestMapping(value="category/{type}")
	public String getCategoryItems(@PathVariable String type) {
		List<CategoryItemDto> result=categoryItemService.getCategoryItemsByType(type);
		return RequestResult.success(result).toJson();
	}
}
