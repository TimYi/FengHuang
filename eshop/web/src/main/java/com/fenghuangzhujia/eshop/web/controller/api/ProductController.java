package com.fenghuangzhujia.eshop.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCaseService;
import com.fenghuangzhujia.eshop.prudoct.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.prudoct.goods.OrderDecoratePackageService;
import com.fenghuangzhujia.eshop.prudoct.goods.dto.PackageGoodDto;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageService;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class ProductController {
	
	@Autowired
	private DecoratePackageService decoratePackageService;
	@Autowired
	private DecorateCaseService decorateCaseService;
	@Autowired
	private OrderDecoratePackageService orderPackageService;
	@Autowired
	private MessageManager messageManager;

	@RequestMapping(value="product/packages",method=RequestMethod.GET)
	public String productList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="8") Integer size) {
		PagedList<DecoratePackageDto> result=decoratePackageService.findPage(page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/package/{id}",method=RequestMethod.GET)
	public String product(@PathVariable String id) {
		DecoratePackageDto result=decoratePackageService.findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/cases",method=RequestMethod.GET)
	public String caseList(@RequestParam(defaultValue="1") Integer page,
	@RequestParam(defaultValue="8")  Integer size) {
		PagedList<DecorateCaseDto> result=decorateCaseService.findPage(page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/case/{id}",method=RequestMethod.GET)
	public String getCase(@PathVariable String id) {
		DecorateCaseDto result=decorateCaseService.findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/package/scramble",method=RequestMethod.POST)
	public String scramble(String id, String caseid) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		String orderid=orderPackageService.scramble(userid, id, caseid);
		return RequestResult.success(orderid).toJson();
	}
	
	@RequestMapping(value="user/package/{id}",method=RequestMethod.GET)
	public String getDetail(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PackageGoodDto result=orderPackageService.getGoodByOrder(userid,id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/package/{id}",method=RequestMethod.POST)
	public String addDetail(@PathVariable String id, String caseid, String areaid, 
			String address, Double houseArea, String remark) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		orderPackageService.addDetail(userid, id, caseid, areaid, address, houseArea, remark);
		return RequestResult.success("修改成功").toJson();
	}
	
	@RequestMapping(value="user/package/comfirm/{id}",method=RequestMethod.POST)
	public String comfirm(@PathVariable String id, String mobile, String validater) {
		messageManager.validate(mobile, validater);
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		orderPackageService.comfirm(userid, id, mobile);
		return RequestResult.success("确认成功").toJson();
	}
}
