package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.collect.CollectService;
import com.fenghuangzhujia.eshop.collect.dto.CollectDto;
import com.fenghuangzhujia.eshop.comment.CommentItemService;
import com.fenghuangzhujia.eshop.comment.dto.CommentItemDto;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.coupons.CouponsService;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class UserController {
	
	@Autowired
	private CollectService collectService;
	@Autowired
	private CommentItemService commentService;
	@Autowired
	private CouponsService couponsService;
	
	@RequestMapping(value="user/collects",method=RequestMethod.GET)
	public String collects(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<CollectDto> collects=collectService.findPage(page, size, userid);
		return RequestResult.success(collects).toJson();
	}
	
	@RequestMapping(value="user/comments",method=RequestMethod.GET)
	public String comments(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<CommentItemDto> comments=commentService.findByUser(page, size, userid);
		return RequestResult.success(comments).toJson();
	}
	
	@RequestMapping(value="user/coupons",method=RequestMethod.GET)
	public String coupons(@RequestParam(defaultValue="true") boolean notUsed) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		List<CouponsDto> result;
		if(notUsed) {
			result=couponsService.findUnUsedCoupons(userid);
		} else {
			result=couponsService.findUserCoupons(userid);
		}
		return RequestResult.success(result).toJson();
	}
}
