package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.artical.ArticalService;
import com.fenghuangzhujia.eshop.artical.dto.ArticalDto;
import com.fenghuangzhujia.eshop.artical.dto.ArticalInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController(value="adminArticleController")
@RequestMapping("admin/artical")
public class ArticleController extends SpecificationController<ArticalDto,ArticalInputArgs> {

	@Autowired
	private ArticalService service;
	
	@Override
	protected ArticalService getService() {
		return service;
	}
}
