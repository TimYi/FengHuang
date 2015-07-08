package com.fenghuangzhujia.eshop.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class CodeGenerater {

	/**
	 * 生成12位订单编号
	 * 以八位日期为起始
	 * 以四位字母为末尾 
	 * @return
	 */
	public static String generateOrderCode() {
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String prefix=format.format(new Date());
		String sufix=RandomStringUtils.random(4,true,true);		
		return prefix.concat(sufix);
	}
	
	/**
	 * 生成12位字符编号
	 * 格式：2位类型代码+区号+4位日期+4位随机字符
	 * @param type 类型代码
	 * @param code 地区编号
	 * @return 12位字符编号
	 */
	public static String generateCode(String type, String code) {
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String date=format.format(new Date());
		date=date.substring(2);
		String sufix=RandomStringUtils.random(4,true,true);
		StringBuilder sb=new StringBuilder();
		sb.append(type);
		sb.append(code);		
		sb.append(date);			
		sb.append(sufix);
		return sb.toString();
	}
}
