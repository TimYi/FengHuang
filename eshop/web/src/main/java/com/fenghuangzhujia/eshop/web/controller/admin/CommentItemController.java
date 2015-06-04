package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.comment.CommentItemService;
import com.fenghuangzhujia.eshop.comment.dto.CommentItemDto;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping("admin/commentItem")
public class CommentItemController extends SpecificationController<CommentItemDto> {

	@Autowired
	private CommentItemService service;
	
	@Override
	protected CommentItemService getService() {
		return service;
	}
}
