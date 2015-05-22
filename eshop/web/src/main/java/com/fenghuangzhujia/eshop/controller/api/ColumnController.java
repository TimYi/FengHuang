package com.fenghuangzhujia.eshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.column.ColumnService;
import com.fenghuangzhujia.eshop.core.column.dto.ColumnDto;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class ColumnController {
	
	@Autowired
	private ColumnService columnService;
	
	@RequestMapping(value="column",method=RequestMethod.POST)
	public String add(ColumnDto column) {
		column=columnService.add(column);
		return RequestResult.success(column).toJson();
	}
	
	@RequestMapping(value="column/{id}",method=RequestMethod.POST)
	public String edit(ColumnDto column) {
		column=columnService.update(column);
		return RequestResult.success(column).toJson();
	}
	
	@RequestMapping(value="column/{id}",method=RequestMethod.GET)
	public String findOne(@PathVariable String id) {
		ColumnDto column=columnService.findOne(id);
		return RequestResult.success(column).toJson();
	}
	
	@RequestMapping(value="columns",method=RequestMethod.GET)
	public String findByType(String type) {
		List<ColumnDto> columns=columnService.findByTypeName(type);
		return RequestResult.success(columns).toJson();
	}
	
	@RequestMapping(value="column/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		columnService.delete(id);
		return RequestResult.success("删除成功").toJson();
	}
}
