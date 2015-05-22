package com.fenghuangzhujia.eshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.utils.PhoneNumberValidator;

@RestController
public class ValidateController {
	
	@Autowired
	private MessageManager manager;
	
	@RequestMapping(value="message",method=RequestMethod.POST)
	public String shortMessage(String mobile) {
		if(!PhoneNumberValidator.isMobile(mobile)) {
			return RequestResult.error(SystemErrorCodes.CAPTCHA_ERROR, "请输入正确的手机号码").toJson();
		}
		manager.create(mobile);
		return RequestResult.success("短信发送成功").toJson();
	}
}
