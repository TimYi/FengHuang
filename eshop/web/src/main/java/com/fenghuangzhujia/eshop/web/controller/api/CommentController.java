package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.comment.CommentItemService;
import com.fenghuangzhujia.eshop.comment.dto.CommentItemDto;
import com.fenghuangzhujia.eshop.comment.dto.CommentItemInputArgs;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class CommentController {

	@Autowired
	private CommentItemService commentService;
	
	@RequestMapping(value="comment",method=RequestMethod.POST)
	public String commentOn(CommentItemInputArgs comment) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		comment.setUserid(userid);
		CommentItemDto result=commentService.add(comment);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="comments",method=RequestMethod.GET)
	public String comments(@RequestParam String sourceid,
			@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="8") int size) {
		PagedList<CommentItemDto> result=commentService.findBySource(page, size, sourceid);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/comments",method=RequestMethod.GET)
	public String comments(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<CommentItemDto> comments=commentService.findByUser(page, size, userid);
		return RequestResult.success(comments).toJson();
	}
}
