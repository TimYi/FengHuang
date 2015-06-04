package com.fenghuangzhujia.foundation.utils.validater;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidater {

	public static final String USERNAME_REGEX="^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,10}$";
	/**
	 * 检验是否是标准的11位手机号码
	 * @param number
	 * @return 手机号码正确返回true
	 */
	public static boolean isUsername(String username) {
		Pattern pattern=Pattern.compile(USERNAME_REGEX);
		Matcher matcher=pattern.matcher(username);
		if(matcher.matches()) {
			return true;
		} else {
			return EmailValidater.isEmail(username);
		}
	}
}
