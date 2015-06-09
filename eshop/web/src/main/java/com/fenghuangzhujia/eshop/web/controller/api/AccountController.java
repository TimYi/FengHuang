package com.fenghuangzhujia.eshop.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.validate.captcha.CaptchaManager;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.utils.Servlets;

@RestController
public class AccountController {
	
	@Autowired
	private MessageManager messageManager;
	
	@Autowired
	AuthenticationManager manager;
	@Autowired
	private CaptchaManager captchaManager;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@RequestParam String username,@RequestParam String password,@RequestParam String captcha,
			HttpServletRequest request) {
		captchaManager.validate(username, captcha);
		String ip=Servlets.getClientIp(request);
		UserToken token=manager.login(username, password, ip);
		String tokenString=token.getToken();
		return RequestResult.success(tokenString).toJson();
	}
	
	/**
	 * 
	 * @param username
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param comfirmPassword 确认密码
	 * @param captcha 图形验证码
	 * @return
	 */
	@RequestMapping(value="changePassword",method=RequestMethod.POST)
	public String changePassword(@RequestParam String username,@RequestParam String oldPassword,
			@RequestParam String newPassword, @RequestParam String comfirmPassword,@RequestParam String captcha) {
		captchaManager.validate(username, captcha);
		if(!newPassword.equals(comfirmPassword))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "两次输入的密码不一致，请重新输入");
		UserToken token=manager.changePassword(username, newPassword, oldPassword);
		return RequestResult.success(token.getToken()).toJson();
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
	public String regist(@RequestParam String username,@RequestParam String password,
			@RequestParam String mobile,@RequestParam String validater, HttpServletRequest request) {
		messageManager.validate(mobile, validater);
		String ip=Servlets.getClientIp(request);
		UserToken token=manager.regist(username, password, ip);
		String tokenString=token.getToken();
		return RequestResult.success(tokenString).toJson();
	}
}
