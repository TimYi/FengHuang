package com.fenghuangzhujia.eshop.prudoct.scramble.dto;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodConverter;
import com.fenghuangzhujia.eshop.prudoct.scramble.PackageGood;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Component
public class PackageGoodDtoConverter implements GoodConverter<PackageGoodDto> {

	@Override
	public boolean support(Good good) {
		if(good.getClass().equals(PackageGood.class))return true;
		return false;
	}

	@Override
	public PackageGoodDto convert(Good good) {
		PackageGood pg=(PackageGood)good;
		PackageGoodDto dto=BeanMapper.map(pg, PackageGoodDto.class);
		return dto;
	}
}
