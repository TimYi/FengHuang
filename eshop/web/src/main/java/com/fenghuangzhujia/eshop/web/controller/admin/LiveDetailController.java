package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.live.LiveDetailService;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping(value="admin/liveDetail")
public class LiveDetailController extends SpecificationController<LiveDetailDto, LiveDetailInputArgs> {

	@Autowired
	private LiveDetailService service;
	
	@Override
	public LiveDetailService getService() {
		return service;
	}
}
