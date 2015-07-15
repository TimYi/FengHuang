package com.fenghuangzhujia.eshop.materialManage.product;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductDto;
import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class ProductService extends DtoSpecificationService<Product, ProductDto, ProductInputArgs, String> {

	public void reOrder(Map<String, Integer> orders) {
		for (Entry<String, Integer> order : orders.entrySet()) {
			String id=order.getKey();
			Integer ordernum=order.getValue();
			Product product=getRepository().findOne(id);
			product.setOrdernum(ordernum);
		}
	}
}
