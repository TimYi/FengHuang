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
		String sufix=RandomStringUtils.random(4);		
		return prefix.concat(sufix);
	}
}
