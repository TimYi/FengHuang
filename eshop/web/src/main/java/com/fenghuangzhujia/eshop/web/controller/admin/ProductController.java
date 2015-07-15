package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.materialManage.product.ProductService;
import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductDto;
import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController("adminProductController")
@RequestMapping("admin/product")
public class ProductController extends SpecificationController<ProductDto, ProductInputArgs> {

	@Autowired
	private ProductService service;
	@Override
	public ProductService getService() {
		return service;
	}
	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String order(@RequestBody Map<String, Integer> orders) {
		getService().reOrder(orders);
		return RequestResult.success("排序完成").toJson();
	}
	
	@RequestMapping("bybrand/{brandId}")
	public String getByBrand(@PathVariable String brandId) {
		List<ProductDto> products=getService().findByBrand(brandId);
		PagedList<ProductDto> result=new PagedList<>(products);
		return RequestResult.success(result).toJson();
	}
}
