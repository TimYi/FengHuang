package com.fenghuangzhujia.eshop.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.commerce.drawback.DrawbackDto;
import com.fenghuangzhujia.eshop.commerce.drawback.DrawbackService;
import com.fenghuangzhujia.eshop.commerce.drawback.Drawback.DrawbackStatus;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
@RequestMapping("admin/drawback")
public class DrawbackController {
	
	@Autowired
	private DrawbackService service;

	@RequestMapping(value="",method=RequestMethod.GET)
	public String page(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size,
			DrawbackStatus status, HttpServletRequest request) {
		PagedList<DrawbackDto> result=service.findByStatus(status, page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String info(@PathVariable String id) {
		DrawbackDto result=service.findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="{id}/approve",method=RequestMethod.POST)
	public String approve(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		service.approve(id, userId);
		return RequestResult.success("批准退款成功").toJson();
	}
	
	@RequestMapping(value="{id}/disapprove",method=RequestMethod.POST)
	public String disapprove(@PathVariable String id, String reason) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		service.disapprove(id, userId, reason);
		return RequestResult.success("拒绝退款成功").toJson();
	}
}
