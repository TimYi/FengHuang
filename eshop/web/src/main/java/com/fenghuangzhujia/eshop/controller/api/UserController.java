package com.fenghuangzhujia.eshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.user.collect.CollectService;
import com.fenghuangzhujia.eshop.user.collect.dto.CollectDto;
import com.fenghuangzhujia.eshop.user.comment.CommentService;
import com.fenghuangzhujia.eshop.user.comment.dto.CommentDto;
import com.fenghuangzhujia.eshop.user.message.MessageService;
import com.fenghuangzhujia.eshop.user.message.dto.MessageDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class UserController {
	
	@Autowired
	private CollectService collectService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageService messageService;
	
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
		PagedList<CommentDto> comments=commentService.findPage(page, size, userid);
		return RequestResult.success(comments).toJson();
	}
	
	@RequestMapping(value="user/messages",method=RequestMethod.GET)
	public String messages(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<MessageDto> comments=messageService.findPage(page, size, userid);
		return RequestResult.success(comments).toJson();
	}
}
