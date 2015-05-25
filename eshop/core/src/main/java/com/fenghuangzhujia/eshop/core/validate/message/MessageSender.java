package com.fenghuangzhujia.eshop.core.validate.message;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.fenghuangzhujia.eshop.core.validate.core.CredentialCreater;
import com.fenghuangzhujia.eshop.core.validate.util.Config;

@Component("messageSender")
public class MessageSender implements CredentialCreater {
	
	private CCPRestSmsSDK smsSDK;
	
	public MessageSender(){
		smsSDK=SmsSDKCreater.getInstance();
	}
	
	@Override
	public Object createCredential(String telephone, String code, Integer expireMiniutes) {
		String[] datas=new String[2];
		datas[0]=code;
		datas[1]=expireMiniutes.toString();
		HashMap<String, Object> result=smsSDK.sendTemplateSMS(telephone, Config.SMS_TEMPLATE, datas);
		if(!"000000".equals(result.get("statusCode"))){
			String message="错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg");
			throw new SendMessageException(message);
		}	
		return null;
	}
}
