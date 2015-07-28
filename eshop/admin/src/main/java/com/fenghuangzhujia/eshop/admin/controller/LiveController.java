package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.live.LiveDetailService;
import com.fenghuangzhujia.eshop.live.LiveService;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDto;
import com.fenghuangzhujia.eshop.live.dto.LiveInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminLiveController")
@RequestMapping(value="live")
public class LiveController extends SpecificationController<LiveDto, LiveInputArgs> {

	@Autowired
	private LiveService service;
	@Autowired
	private LiveDetailService liveDetailService;
	
	@Override
	public LiveService getService() {
		return service;
	}
	
	@RequestMapping(value="{id}/details",method=RequestMethod.GET)
	public String details(@PathVariable String id,
			@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="8") int size) {
		PagedList<LiveDetailDto> details=liveDetailService.liveDetailPage(id, page, size);
		return RequestResult.success(details).toJson();
	}
}
