package com.fenghuangzhujia.eshop.core.validate.message;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.fenghuangzhujia.eshop.core.validate.util.Config;

public class SmsSDKCreater {
	private static CCPRestSmsSDK smsSDK;
	
	public static CCPRestSmsSDK getInstance(){
		if(smsSDK==null){
			smsSDK=new CCPRestSmsSDK();
			smsSDK.init(Config.SMS_SITE, Config.SMS_PORT);
			smsSDK.setAccount(Config.SMS_ACCOUNT, Config.SMS_PASSWORD);
			smsSDK.setAppId(Config.SMS_APPID);
		}
		return smsSDK;
	}
}
