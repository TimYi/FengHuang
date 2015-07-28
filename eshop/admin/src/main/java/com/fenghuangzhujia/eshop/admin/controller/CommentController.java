package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.comment.CommentService;
import com.fenghuangzhujia.eshop.comment.dto.CommentDto;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminCommentController")
@RequestMapping("comment")
public class CommentController extends SpecificationController<CommentDto,CommentDto> {
	
	@Autowired
	private CommentService service;

	@Override
	protected CommentService getService() {
		return service;
	}

}
