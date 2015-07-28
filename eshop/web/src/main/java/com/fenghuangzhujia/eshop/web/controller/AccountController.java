package com.fenghuangzhujia.eshop.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.UserService;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
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
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@RequestParam String username,@RequestParam String password,@RequestParam String captcha,
			HttpServletRequest request) {
		captchaManager.validate(username, captcha);
		String ip=Servlets.getClientIp(request);
		UserToken token=manager.login(username, password, ip);
		String tokenString=token.getToken();
		String userId=token.getUser().getId();
		UserDto profile=userService.findOne(userId);
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("token", tokenString);
		result.put("profile", profile);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="logout",method=RequestMethod.POST)
	public String logout(String token) {
		manager.logout(token);
		return RequestResult.success("注销成功").toJson();
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
			@RequestParam String newPassword, @RequestParam String confirmPassword,@RequestParam String captcha) {
		captchaManager.validate(username, captcha);
		if(!newPassword.equals(confirmPassword))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "两次输入的密码不一致，请重新输入");
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
		UserToken token=manager.regist(username, password, mobile, ip);
		String tokenString=token.getToken();
		return RequestResult.success(tokenString).toJson();
	}
	
	@RequestMapping(value="forgetPassword", method=RequestMethod.POST)
	public String forgetPassword(@RequestParam String username,@RequestParam String telephone,
			@RequestParam String validater) {
		messageManager.validate(telephone, validater);
		String code=manager.forgetPassword(username, telephone);
		return RequestResult.success(code).toJson();
	}
	
	@RequestMapping(value="changeForgotPassword", method=RequestMethod.POST)
	public String changeForgotPassword(@RequestParam String username, @RequestParam String validater,
			@RequestParam String password) {
		manager.changeForgotPassword(username, validater, password);
		return RequestResult.success("修改成功").toJson();
	}
}
