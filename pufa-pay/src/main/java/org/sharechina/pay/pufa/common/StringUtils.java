package org.sharechina.pay.pufa.common;

public class StringUtils {

	public static String firstCharToUpper(String str) {
		char[] chars=new char[1];
		chars[0]=str.charAt(0);  
        String temp=new String(chars);  
        return str.replaceFirst(temp, temp.toUpperCase());
	}
	
	public static String firstCharToLower(String str) {
		char[] chars=new char[1];
		chars[0]=str.charAt(0);  
        String temp=new String(chars);  
        return str.replaceFirst(temp, temp.toLowerCase());
	}
}
