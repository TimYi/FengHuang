package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.decorateProcess.DecorateProcessService;
import com.fenghuangzhujia.eshop.decorateProcess.dto.DecorateProcessDto;
import com.fenghuangzhujia.eshop.decorateProcess.dto.DecorateProcessInputArgs;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("decorateProcess")
public class DecorateProcessController extends PagingController<DecorateProcessDto, DecorateProcessInputArgs> {

	@Autowired
	private DecorateProcessService service;
	
	@Override
	public DecorateProcessService getService() {
		return service;
	}
}
