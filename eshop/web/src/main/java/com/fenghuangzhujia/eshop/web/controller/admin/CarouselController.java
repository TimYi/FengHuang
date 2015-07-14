package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="bypage/{pageId}", method=RequestMethod.GET)
	public String findByPage(@PathVariable String pageId) {
		List<CarouselDto> result=getService().findByPage(pageId);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String reOrder(@RequestBody Map<String, Integer> orders) {
		getService().reOrder(orders);
		return RequestResult.success("排序完成").toJson();
	}
}
