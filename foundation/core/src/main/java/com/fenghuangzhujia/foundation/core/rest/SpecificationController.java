package com.fenghuangzhujia.foundation.core.rest;

import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.foundation.core.service.SpecifactionService;

@RestController
public abstract class SpecificationController<T> extends PagingController<T> {

	@Override
	protected abstract SpecifactionService<T, String> getService();

}
