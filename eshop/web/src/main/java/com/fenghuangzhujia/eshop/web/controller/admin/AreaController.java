package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.area.AreaService;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.area.dto.AreaInputArgs;
import com.fenghuangzhujia.eshop.core.area.dto.AreaVo;
import com.fenghuangzhujia.foundation.core.rest.PagingController;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController(value="adminAreaController")
@RequestMapping("admin/area")
public class AreaController extends PagingController<AreaDto,AreaInputArgs> {

	@Autowired
	private AreaService service;
	
	@Override
	public AreaService getService() {
		return service;
	}
	
	@RequestMapping(value="level/{level}")
	public String findByLevel(@PathVariable AreaLevel level) {
		List<AreaVo> result=getService().findVoByLevel(level);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="upper/{id}")
	public String findByUpper(@PathVariable String id) {
		List<AreaDto> result=getService().findByUpperArea(id);
		return RequestResult.success(result).toJson();
	}
}
