package com.fenghuangzhujia.eshop.web.utils;

import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentUtil {

	public static BrowserType getBrowserType(HttpServletRequest request) {
		String userAgentString=request.getHeader("User-Agent");
		UserAgent userAgent=UserAgent.parseUserAgentString(userAgentString);
		BrowserType browserType=userAgent.getBrowser().getBrowserType();
		return browserType;
	}
}
