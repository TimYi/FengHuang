package com.fenghuangzhujia.eshop.core.rlmessage;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 封装后的短信发送器
 * @author pc
 *
 */
@Component("messageSender2")
public class MessageSender {
		
	private CCPRestSmsSDK smsSDK;
	
	public MessageSender(){
		smsSDK=SmsSDKCreater.getInstance();
	}
	
	public void sendMessage(String telephone, String template, String[] datas) {
		HashMap<String, Object> result=smsSDK.sendTemplateSMS(telephone, template, datas);
		if(!"000000".equals(result.get("statusCode"))){
			String message="错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg");
			throw new SendMessageException(message);
		}	
	}
	
	/**
	 * 封装预约成功短信通知
	 * @param telephone
	 */
	public void appointSuccess(String telephone) {
		sendMessage(telephone, Config.APPOINT_SUCCESS_TEMPLATE, null);
	}
	
	public void paySuccess(String telephone, Double money) {
		String[] datas=new String[1];
		datas[0]=money.toString();
		sendMessage(telephone, Config.PAY_SUCCESS_TEMPLATE, datas);
	}
}
