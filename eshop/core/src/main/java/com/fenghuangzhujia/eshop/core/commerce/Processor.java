package com.fenghuangzhujia.eshop.core.commerce;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

/**
 * 商品订单处理器
 * @author Administrator
 *
 */
@Component
public class Processor {

	@Autowired
	private Set<Checker> checkers;
	
	/**
	 * 检查商品订单，执行扣除库存等操作。如果不能正确执行，抛出异常。
	 * @param t
	 */
	public void process(GoodOrder t) throws ErrorCodeException {
		for(Checker checker:checkers){
			if(checker.support(t)){
				checker.process(t);
			}
		}
	}
	
}
