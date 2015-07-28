package com.fenghuangzhujia.eshop.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.admin.auth.SystemAuthManager;
import com.fenghuangzhujia.eshop.admin.user.SystemToken;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.validate.captcha.CaptchaManager;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController(value="adminAccountController")
public class AccountController {
	
	@Autowired
	private CaptchaManager captchaManager;
	@Autowired
	private SystemAuthManager manager;

	@RequestMapping(value="unauto")
	public String unauth() {
		return RequestResult.error(SystemErrorCodes.UNAUTH, "您需要登录").toJson();
	}
	
	@RequestMapping(value="auth")
	public String auth() {
		return RequestResult.success("授权成功").toJson();
	}
	
	@RequestMapping(value="accessDenied")
	public String accessDeny() {
		return RequestResult.error(SystemErrorCodes.ACCESS_DENIED, "权限不足").toJson();
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(String username, String password, String captcha) {
		captchaManager.validate(username, captcha);
		SystemToken token=manager.login(username, password);
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("token", token.getToken());
		result.put("username", token.getUser().getUsername());
		return RequestResult.success(result).toJson();
	}
}
