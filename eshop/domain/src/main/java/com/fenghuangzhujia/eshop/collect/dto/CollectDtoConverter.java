package com.fenghuangzhujia.eshop.collect.dto;

import com.fenghuangzhujia.eshop.collect.Collect;

public interface CollectDtoConverter {

	/**
	 * 是否支持某种dto对象
	 * @param t
	 * @return
	 */
	boolean support(CollectDto t);
	
	<T extends Collect> T convert(CollectDto t);
	
	<T extends Collect> T update(CollectDto t,Collect d);
}
