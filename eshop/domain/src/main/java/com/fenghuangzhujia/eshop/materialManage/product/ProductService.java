package com.fenghuangzhujia.eshop.materialManage.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductDto;
import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class ProductService extends DtoSpecificationService<Product, ProductDto, ProductInputArgs, String> {

	public void reOrder(String[] ids) {
		int i=0;
		for (String id : ids) {
			Product product=getRepository().findOne(id);
			product.setOrdernum(i);
			i++;
		}
	}
}
