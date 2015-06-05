package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.foundation.area.Area.AreaLevel;
import com.fenghuangzhujia.foundation.area.dto.AreaDto;
import com.fenghuangzhujia.foundation.area.AreaService;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	@RequestMapping("area/level/{level}")
	public String areaByLevel(@PathVariable AreaLevel level) {
		List<AreaDto> result=areaService.findByLevel(level);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping("area/subsof/{id}")
	public String areaByUpper(@PathVariable String id) {
		List<AreaDto> result=areaService.findByUpperArea(id);
		return RequestResult.success(result).toJson();
	}
}
