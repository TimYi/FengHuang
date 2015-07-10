package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.materialManage.brand.BrandService;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandVo;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class MeteriaController {
	
	@Autowired
	private BrandService brandService;

	@RequestMapping(value="meterias",method=RequestMethod.GET)
	public String meterias() {
		List<BrandVo> result=brandService.getDetailedBrands();
		return RequestResult.success(result).toJson();
	}
}
