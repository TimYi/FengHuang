package com.fenghuangzhujia.eshop.core.brand.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.brand.Brand;
import com.fenghuangzhujia.eshop.core.brand.BrandRepository;
import com.fenghuangzhujia.eshop.core.brand.BrandType;
import com.fenghuangzhujia.eshop.core.brand.BrandTypeRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;

@Component
public class BrandDtoAdapter extends AbstractDtoAdapter<Brand, BrandDto> {
	
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private BrandTypeRepository brandTypeRepository;

	@Override
	public BrandDto postConvert(Brand d, BrandDto t) {
		t.setBrandType(d.getBrandType().getName());
		return t;
	}

	@Override
	public Brand postConvertToDo(BrandDto t, Brand d) {
		return postUpdate(t, d);
	}

	@Override
	public Brand postUpdate(BrandDto t, Brand d) {
		String superBrandId=t.getSuperBrandId();
		if(StringUtils.isNotBlank(superBrandId)) {
			Brand superBrand=brandRepository.findOne(superBrandId);
			d.setSuperBrand(superBrand);
		}
		String brandTypeId=t.getBrandTypeId();
		if(StringUtils.isNotBlank(brandTypeId)) {
			BrandType brandType=brandTypeRepository.findOne(brandTypeId);
			d.setBrandType(brandType);
		}
		return d;
	}

}
