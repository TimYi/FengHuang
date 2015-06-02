package com.fenghuangzhujia.eshop.core.validate.core;

public interface ValidateManager extends Validate {
	/**
	 * 根据id创建凭据，根据需要，返回需要展示给用户的凭据内容
	 * @param id
	 * @return 凭据内容
	 */
	Object create(String id);
}
