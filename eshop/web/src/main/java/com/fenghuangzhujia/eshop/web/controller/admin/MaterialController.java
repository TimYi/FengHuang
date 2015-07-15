package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.materialManage.material.MaterialService;
import com.fenghuangzhujia.eshop.materialManage.material.MaterialType;
import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialDto;
import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController("adminMeteriaController")
@RequestMapping("admin/material")
public class MaterialController extends SpecificationController<MaterialDto, MaterialInputArgs> {

	@Autowired
	private MaterialService service;
	
	@Override
	public MaterialService getService() {
		return service;
	}
	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String order(@RequestBody Map<String, Integer> orders) {
		getService().reOrder(orders);
		return RequestResult.success("排序完成").toJson();
	}
	
	@RequestMapping(value="byproduct/{productId}", method=RequestMethod.GET)
	public String byProduct(@PathVariable String productId) {
		List<MaterialDto> materials=getService().findByProduct(productId);
		PagedList<MaterialDto> result=new PagedList<>(materials);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="types",method=RequestMethod.GET)
	public String getTypes() {
		List<MaterialType> types=getService().getMaterialTypes();
		return RequestResult.success(types).toJson();
	}
	
	@RequestMapping(value="types/reorder",method=RequestMethod.POST)
	public String reorderTypes(String[] ids) {
		getService().reOrderMaterialTypes(ids);
		return RequestResult.success("排序完成").toJson();
	}
}
