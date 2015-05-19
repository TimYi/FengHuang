package com.fenghuangzhujia.eshop.core.validate.core;

public interface ValidaterCreater {
	Validater create(String id);
	Integer getExpireMinutes();
	void setExpireMinutes(Integer expireMinutes);
}
