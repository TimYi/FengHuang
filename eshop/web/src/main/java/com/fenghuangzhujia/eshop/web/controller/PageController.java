package com.fenghuangzhujia.eshop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.activity.signup.Signup;
import com.fenghuangzhujia.eshop.activity.signup.SignupService;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.decorateProcess.DecorateProcessService;
import com.fenghuangzhujia.eshop.decorateProcess.dto.DecorateProcessDto;
import com.fenghuangzhujia.eshop.message.MessageService;
import com.fenghuangzhujia.eshop.message.dto.MessageInputArgs;
import com.fenghuangzhujia.eshop.view.carousel.CarouselService;
import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselDto;
import com.fenghuangzhujia.eshop.view.decorateTechnology.DecorateTechnologyService;
import com.fenghuangzhujia.eshop.view.decorateTechnology.dto.DecorateTechnologyDto;
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
	@Autowired
	private DecorateTechnologyService technologyService;
	@Autowired
	private SignupService signupService;
	@Autowired
	private MessageService messageService;

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
	
	@RequestMapping(value="technologies",method=RequestMethod.GET)
	public String technologies() {
		Iterable<DecorateTechnologyDto> result=technologyService.findAll();
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="signup",method=RequestMethod.POST)
	public String signup(Signup signup) {	
		signupService.add(signup);
		
		//如果用户已经登录，发送留言
		if(AuthenticationService.isLogin()) {
			SimpleUserDetails details=AuthenticationService.getUserDetail();
			String userId=details.getId();
			MessageInputArgs args=new MessageInputArgs();
			args.setUserid(userId);
			args.setTitle("情歌对唱报名成功");
			args.setContent("您已成功报名凤凰筑家“为爱而唱，为家而唱”情歌对唱赛！");
			messageService.add(args);
		}				
		return RequestResult.success("报名成功").toJson();
	}
}
