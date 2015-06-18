package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.live.LiveService;
import com.fenghuangzhujia.eshop.live.dto.LiveDto;
import com.fenghuangzhujia.eshop.live.dto.LiveInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminLiveController")
@RequestMapping(value="admin/live")
public class LiveController extends SpecificationController<LiveDto, LiveInputArgs> {

	@Autowired
	private LiveService service;
	
	@Override
	public LiveService getService() {
		return service;
	}
}
