package com.fenghuangzhujia.eshop.web.controller.api;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.validate.captcha.CaptchaManager;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.eshop.utils.web.ImageResponseUtil;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.utils.PhoneNumberValidator;

@RestController
public class ValidateController {
	
	@Autowired
	private MessageManager manager;
	@Autowired
	private CaptchaManager captchaManager;
	
	@RequestMapping(value="message",method=RequestMethod.POST)
	public String shortMessage(@RequestParam String mobile,@RequestParam String captcha) {
		captchaManager.validate(mobile, captcha);
		if(!PhoneNumberValidator.isMobile(mobile)) {
			return RequestResult.error(SystemErrorCodes.CAPTCHA_ERROR, "请输入正确的手机号码").toJson();
		}
		manager.create(mobile);
		return RequestResult.success("短信发送成功").toJson();
	}
	
	@RequestMapping(value="captcha",method=RequestMethod.GET)
	public void captcha(@RequestParam String id,HttpServletResponse response) {
		BufferedImage image=captchaManager.create(id);
		ImageResponseUtil.writeBufferedImage(response, image, "png");
	}
}
