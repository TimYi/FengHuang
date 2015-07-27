package com.fenghuangzhujia.eshop.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.prudoct.packages.space.DecorateSpaceService;
import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateSpaceDto;
import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateSpaceInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
public class DecorateSpaceController extends SpecificationController<DecorateSpaceDto, DecorateSpaceInputArgs> {

	@Autowired
	private DecorateSpaceService service;
	
	@Override
	public DecorateSpaceService getService() {
		return service;
	}
	
	@RequestMapping(value="bypackage/{packageId}")
	public String findByPackage(@PathVariable String packageId) {
		List<DecorateSpaceDto> spaces=service.findByPackage(packageId);
		PagedList<DecorateSpaceDto> result=new PagedList<>(spaces);
		return RequestResult.success(result).toJson();
	}
}
