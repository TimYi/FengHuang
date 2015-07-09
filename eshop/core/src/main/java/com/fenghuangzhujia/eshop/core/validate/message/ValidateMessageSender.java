package com.fenghuangzhujia.eshop.core.validate.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.rlmessage.Config;
import com.fenghuangzhujia.eshop.core.rlmessage.MessageSender;
import com.fenghuangzhujia.eshop.core.validate.core.CredentialCreater;

@Component("messageSender")
public class ValidateMessageSender implements CredentialCreater {
	
	@Autowired
	private MessageSender messageSender;
	
	@Override
	public Object createCredential(String telephone, String code, Integer expireMiniutes) {
		String[] datas=new String[2];
		datas[0]=code;
		datas[1]=expireMiniutes.toString();
		messageSender.sendMessage(telephone, Config.SMS_TEMPLATE, datas);
		return null;
	}
}
