package com.fenghuangzhujia.eshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.area.AreaService;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
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
	
	@RequestMapping("area/subareas/{id}")
	public String areaByUpper(@PathVariable String id) {
		List<AreaDto> result=areaService.findByUpperArea(id);
		return RequestResult.success(result).toJson();
	}
}
