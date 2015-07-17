package com.fenghuangzhujia.eshop.core.rlmessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public void appointSuccess(String mobile, Date date, String city, String code, String realName, String telephone) {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] params={realName,format.format(date),city,code,realName,telephone};
		sendMessage(mobile, Config.APPOINT_SUCCESS_TEMPLATE, params);
	}
	
	public void paySuccess(String telephone, String realName, Date date, String packageName, Double money, String orderCode) {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] datas={realName,format.format(date),packageName,money.toString(),orderCode};
		sendMessage(telephone, Config.PAY_SUCCESS_TEMPLATE, datas);
	}
}
