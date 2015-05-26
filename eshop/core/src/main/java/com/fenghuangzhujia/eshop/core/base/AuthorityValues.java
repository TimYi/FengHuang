package com.fenghuangzhujia.eshop.core.base;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class AuthorityValues {
	public static final String PREFIX="ROLE_";
	public static final String DESIGNER;
	public static final String ADMIN;
	public static final String ENTER;
	static {
		try {
			Configuration configuration=new PropertiesConfiguration("authorities.properties");
			DESIGNER=PREFIX+configuration.getString("authority.designer");
			ADMIN=PREFIX+configuration.getString("authority.admin");
			ENTER=PREFIX+configuration.getString("authority.enter");
		} catch (Exception e) {
			throw new Error(e);
		}		
	}
}
