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
	 * 格式：2位类型代码+6位日期+4位随机字符
	 * @param type 类型代码，2位
	 * @return 12位字符编号
	 */
	public static String generateCode(String type) {
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String prefix=format.format(new Date());
		prefix=prefix.substring(2);
		prefix=type.substring(0, 2)+prefix;
		String sufix=RandomStringUtils.random(4,true,true);		
		return prefix.concat(sufix);
	}
}
