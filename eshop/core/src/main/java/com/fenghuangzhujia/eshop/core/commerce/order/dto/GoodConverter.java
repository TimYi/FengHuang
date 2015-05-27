package com.fenghuangzhujia.eshop.core.commerce.order.dto;

import com.fenghuangzhujia.eshop.core.commerce.goods.Good;

/**
 * 用于将商品转换为dto对象
 * @author pc
 *
 */
public interface GoodConverter<T> {
	boolean support(Good good);
	T convert(Good good);
}
