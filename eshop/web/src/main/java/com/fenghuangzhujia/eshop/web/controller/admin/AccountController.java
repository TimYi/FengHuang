package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController(value="adminAccountController")
public class AccountController {

	@RequestMapping(value="admin/login")
	public String login() {
		return RequestResult.error(SystemErrorCodes.UNAUTH, "您需要登录").toJson();
	}
	
	@RequestMapping(value="admin/auth")
	public String auth() {
		return RequestResult.success("授权成功").toJson();
	}
	
	@RequestMapping(value="/accessDenied")
	public String accessDeny() {
		return RequestResult.error(SystemErrorCodes.ACCESS_DENIED, "权限不足").toJson();
	}
	
	
}
