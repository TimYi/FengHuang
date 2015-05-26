package com.fenghuangzhujia.eshop.core.validate.core;

import java.util.Date;

public interface Validater extends Validate {
	String getId();
	String getCode();	
	/**
	 * 过期时间
	 * @return
	 */
	Date getExpireTime();
}
