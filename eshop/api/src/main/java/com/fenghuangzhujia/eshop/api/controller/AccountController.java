package com.fenghuangzhujia.eshop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class AccountController {
	
	@Autowired
	private MessageManager messageManager;
	
	@Autowired
	AuthenticationManager manager;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(String username, String password) {
		UserToken token=manager.login(username, password);
		String tokenString=token.getToken();
		return RequestResult.success(tokenString).toJson();
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param mobile 手机号码
	 * @param validater 短信验证码
	 * @return
	 */
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public String regist(String username, String password, String mobile, String validater) {
		messageManager.validate(mobile, validater);
		UserToken token=manager.regist(username, password);
		String tokenString=token.getToken();
		return RequestResult.success(tokenString).toJson();
	}
}
