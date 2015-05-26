package com.fenghuangzhujia.eshop.core.commerce;

import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

/**
 * 检查某种商品订单
 * @author pc
 *
 */
public interface Checker {

	
	public boolean support(GoodOrder o);
	
	public void process(GoodOrder o) throws ErrorCodeException;
}
