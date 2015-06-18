package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.cases.CaseTag;
import com.fenghuangzhujia.eshop.cases.DecorateCaseService;
import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppointService;
import com.fenghuangzhujia.eshop.prudoct.appoint.dto.PackageAppointDto;
import com.fenghuangzhujia.eshop.prudoct.appoint.dto.PackageAppointInputArgs;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageService;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;
import com.fenghuangzhujia.eshop.prudoct.scramble.ScrambleService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class ProductController {
	
	@Autowired
	private DecoratePackageService decoratePackageService;
	@Autowired
	private DecorateCaseService decorateCaseService;
	@Autowired
	private ScrambleService orderPackageService;
	@Autowired
	private MessageManager messageManager;
	@Autowired
	private PackageAppointService packageAppointService;

	@RequestMapping(value="product/packages",method=RequestMethod.GET)
	public String productList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="8") Integer size) {
		String userId;
		try {
			SimpleUserDetails details=AuthenticationService.getUserDetail();
			userId=details.getId();
		} catch (Exception e) {
			userId=null;
		}
		PagedList<DecoratePackageDto> result=decoratePackageService.findPage(page, size, userId);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/package/{id}",method=RequestMethod.GET)
	public String product(@PathVariable String id) {
		DecoratePackageDto result=decoratePackageService.findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/cases/tags",method=RequestMethod.GET)
	public String caseTags() {
		List<CaseTag> result=decorateCaseService.findAllTags();
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/cases",method=RequestMethod.GET)
	public String caseList(@RequestParam(defaultValue="1") Integer page,
	@RequestParam(defaultValue="8")  Integer size, String[] tags) {
		Set<String> tagSet=new HashSet<String>();
		if(tags!=null) {
			for (String tag : tags) {
				tagSet.add(tag);
			}
		}	
		PagedList<DecorateCaseDto> result=decorateCaseService.findByTags(tagSet, page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/case/{id}",method=RequestMethod.GET)
	public String getCase(@PathVariable String id) {
		DecorateCaseDto result=decorateCaseService.findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/package/{decoratePackageId}/appoint",method=RequestMethod.POST)
	public String scramble(PackageAppointInputArgs args) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		args.setUserId(userId);
		PackageAppointDto result=packageAppointService.appointByUser(args);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/package/scramble",method=RequestMethod.POST)
	public String scramble(String id, String caseId) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		GoodOrderDto result=orderPackageService.scramble(userId, id, caseId);
		return RequestResult.success(result).toJson();
	}
	
}
