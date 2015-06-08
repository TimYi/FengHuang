package com.fenghuangzhujia.eshop.core.brand.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.brand.BrandType;
import com.fenghuangzhujia.eshop.core.brand.BrandTypeRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class BrandTypeDtoAdapter extends AbstractDtoAdapter<BrandType, BrandTypeDto, BrandTypeDto> {

	@Autowired
	private BrandTypeRepository brandTypeRepository;
	
	@Override
	public BrandTypeDto postConvert(BrandType d, BrandTypeDto t) {
		return t;
	}

	@Override
	public BrandType postConvertToDo(BrandTypeDto t, BrandType d) {
		return postUpdate(t, d);
	}

	@Override
	public BrandType postUpdate(BrandTypeDto t, BrandType d) {
		String superTypeId=t.getSuperTypeId();
		if(StringUtils.isNotBlank(superTypeId)) {
			BrandType superType=brandTypeRepository.findOne(superTypeId);
			d.setSuperType(superType);
		}
		return d;
	}

}
