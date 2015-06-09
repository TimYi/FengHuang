package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.message.MessageService;
import com.fenghuangzhujia.eshop.message.dto.MessageDto;
import com.fenghuangzhujia.eshop.message.dto.MessageInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping("admin/message")
public class MessageController extends SpecificationController<MessageDto, MessageInputArgs> {

	@Autowired
	private MessageService service;
	
	@Override
	public MessageService getService() {
		return service;
	}
}
