package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.commerce.order.GoodOrderService;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;
import com.fenghuangzhujia.foundation.utils.Servlets;

@RestController(value="adminOrderController")
@RequestMapping("admin/order")
public class OrderController {

	@Autowired
	private GoodOrderService service;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String page(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8")  Integer size, 
			HttpServletRequest request) {
		Map<String, Object> searchs=Servlets.getParametersStartingWith(request, SpecificationController.SEARCH_PREFIX);
		Map<String, Object> sorts=Servlets.getParametersStartingWith(request, SpecificationController.SORT_PREFIX);
		PagedList<GoodOrderDto> result=service.findAll(page, size, searchs, sorts);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="status/{status}",method=RequestMethod.GET)
	public String findByStatus(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="8")  Integer size, @PathVariable OrderStatus status) {
		PagedList<GoodOrderDto> result=service.findByStatus(page, size, status);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String info(@PathVariable String id) {
		GoodOrderDto result=service.findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="{id}/changeStatus",method=RequestMethod.POST)
	public String changeStatus(@PathVariable String id, OrderStatus status) {
		service.changeStatus(id, status);
		return RequestResult.success("修改成功").toJson();
	}
}
