package com.fenghuangzhujia.eshop.materialManage.product.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.materialManage.brand.Brand;
import com.fenghuangzhujia.eshop.materialManage.brand.BrandRepository;
import com.fenghuangzhujia.eshop.materialManage.product.Product;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class ProductAdapter extends AbstractDtoAdapter<Product, ProductDto, ProductInputArgs> {
	
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public ProductDto postConvert(Product d, ProductDto t) {
		return t;
	}

	@Override
	public Product postConvertToDo(ProductInputArgs i, Product d) {
		return postUpdate(i, d);
	}

	@Override
	public Product postUpdate(ProductInputArgs i, Product d) {
		String brandId=i.getBrandId();
		if(StringUtils.isNotBlank(brandId)) {
			Brand brand=brandRepository.findOne(i.getBrandId());
			d.setBrand(brand);
		}		
		return d;
	}
}
