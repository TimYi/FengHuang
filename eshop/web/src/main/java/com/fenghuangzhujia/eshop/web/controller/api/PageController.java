package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.decorateProcess.DecorateProcessService;
import com.fenghuangzhujia.eshop.decorateProcess.dto.DecorateProcessDto;
import com.fenghuangzhujia.eshop.view.carousel.CarouselService;
import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselDto;
import com.fenghuangzhujia.eshop.view.navigation.NavigationService;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationDto;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class PageController {
	
	@Autowired
	private NavigationService navigationService;
	@Autowired
	private CarouselService carouselService;
	@Autowired
	private DecorateProcessService decorateProcessService;

	@RequestMapping(value="navigations",method=RequestMethod.GET)
	public String navagations() {
		Iterable<NavigationDto> result=navigationService.findAll();
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="page/{pageId}/carousels",method=RequestMethod.GET)
	public String carousels(@PathVariable String pageId) {
		Iterable<CarouselDto> result=carouselService.findByPage(pageId);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="decorateProcess",method=RequestMethod.GET)
	public String decorateProcess() {
		Iterable<DecorateProcessDto> result=decorateProcessService.findAll();
		return RequestResult.success(result).toJson();
	}
}
