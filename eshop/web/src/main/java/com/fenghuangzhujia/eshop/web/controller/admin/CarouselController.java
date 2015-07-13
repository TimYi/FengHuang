package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.view.carousel.CarouselService;
import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselDto;
import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping("admin/carousel")
public class CarouselController extends SpecificationController<CarouselDto, CarouselInputArgs> {

	@Autowired
	private CarouselService service;
	
	@Override
	public CarouselService getService() {
		return service;
	}
	
	@RequestMapping("bypage/{pageId}")
	public String findByPage(@PathVariable String pageId) {
		List<CarouselDto> result=getService().findByPage(pageId);
		return RequestResult.success(result).toJson();
	}
}
