package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.message.MessageService;
import com.fenghuangzhujia.eshop.message.dto.MessageDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="user/messages",method=RequestMethod.GET)
	public String messages(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<MessageDto> result=messageService.findByUser(userid, page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/message/{id}",method=RequestMethod.GET)
	public String getMessage(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		MessageDto result=messageService.findOneByUser(userid, id);
		return RequestResult.success(result).toJson();
	}
}
