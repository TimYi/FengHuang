package com.fenghuangzhujia.eshop.materialManage.product;

import java.util.List;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface ProductRepository extends SpecificationRepository<Product, String> {

	List<Product> findByBrandId(String brandId);
}
