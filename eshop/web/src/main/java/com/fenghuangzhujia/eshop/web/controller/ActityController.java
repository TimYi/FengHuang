package com.fenghuangzhujia.eshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.activity.signup.Signup;
import com.fenghuangzhujia.eshop.activity.signup.SignupService;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.message.MessageService;
import com.fenghuangzhujia.eshop.message.dto.MessageInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class ActityController {

	@Autowired
	private SignupService signupService;
	@Autowired
	private MessageService messageService;
	
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
	
	@RequestMapping(value="signup/query",method=RequestMethod.GET)
	public String signupQuery(String telephone) {	
		List<Signup> signups=signupService.query(telephone);			
		return RequestResult.success(signups).toJson();
	}
	
}
