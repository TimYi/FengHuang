package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCaseService;
import com.fenghuangzhujia.eshop.prudoct.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("admin/case")
public class CaseController extends PagingController<DecorateCaseDto> {

	@Autowired
	private DecorateCaseService service;
	
	@Override
	public DecorateCaseService getService() {
		return service;
	}
}
