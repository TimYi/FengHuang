package com.fenghuangzhujia.eshop.core.validate.core;

import com.fenghuangzhujia.eshop.core.validate.core.exception.ValidateException;

public interface Validate {
	void validate(String id, String code) throws ValidateException;
}
