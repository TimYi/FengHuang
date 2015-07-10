package com.fenghuangzhujia.eshop.core.utils;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class LogUtils {

	public static Logger logger=LoggerFactory.getLogger(LogUtils.class);
	
	public static void errorLog(String msg) {
		logger.error(msg);
	}
	
	public static void errorLog(Throwable ex) {
		logger.error(ex.getMessage(), ex);
	}
	
	public static void infoLog(String message) {
		logger.info(message);
	}
}
