package com.fenghuangzhujia.eshop.meteriaManage.product.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.meteriaManage.brand.Brand;
import com.fenghuangzhujia.eshop.meteriaManage.brand.BrandRepository;
import com.fenghuangzhujia.eshop.meteriaManage.product.Product;
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
		Brand brand=brandRepository.findOne(i.getBrandId());
		d.setBrand(brand);
		return d;
	}

	@Override
	public Product postUpdate(ProductInputArgs i, Product d) {
		return d;
	}
}
