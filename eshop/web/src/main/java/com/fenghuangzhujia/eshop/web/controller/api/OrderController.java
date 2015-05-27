package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrderService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class OrderController {
	
	@Autowired
	private GoodOrderService orderService;

	@RequestMapping(value="user/orders",method=RequestMethod.GET)
	public String list(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="8") Integer size, OrderStatus status) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<GoodOrderDto> orders=orderService.findByUser(userid, page, size, status);
		return RequestResult.success(orders).toJson();
	}
	
	@RequestMapping(value="user/order/{id}",method=RequestMethod.GET)
	public String getOne(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		GoodOrderDto result=orderService.findOneByUser(userid, id);
		return RequestResult.success(result).toJson();
	}
}
