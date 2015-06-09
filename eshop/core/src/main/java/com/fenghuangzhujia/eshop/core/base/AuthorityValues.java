package com.fenghuangzhujia.eshop.core.base;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class AuthorityValues {
	public static final String PREFIX="ROLE_";
	public static final String WORKER;
	public static final String ADMIN;
	static {
		try {
			Configuration configuration=new PropertiesConfiguration("authorities.properties");
			ADMIN=configuration.getString("authority.admin");
			WORKER=configuration.getString("authority.worker");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
}
