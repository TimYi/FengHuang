package com.fenghuangzhujia.eshop.core.validate.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 统一读取配置信息
 * @author pc
 *
 */
public class Config {
	public static final String SMS_SITE;
	public static final String SMS_PORT;
	public static final String SMS_ACCOUNT;
	public static final String SMS_PASSWORD;
	public static final String SMS_APPID;
	public static final String SMS_TEMPLATE;
	
	private static Configuration configuration;
	
	static {
		try {
			configuration=new PropertiesConfiguration("cms.properties");			
		} catch (ConfigurationException e) {
		}
		SMS_SITE=configuration.getString("sms.site");
		SMS_PORT=configuration.getString("sms.port");
		SMS_ACCOUNT=configuration.getString("sms.account");
		SMS_PASSWORD=configuration.getString("sms.password");
		SMS_APPID=configuration.getString("sms.appid");
		SMS_TEMPLATE=configuration.getString("sms.template");
	}
}
