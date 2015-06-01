package com.fenghuangzhujia.eshop.coupons.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.coupons.entity.CouponsConstrainEntity;
import com.fenghuangzhujia.foundation.core.dto.SimpleDtoAdapter;
import com.fenghuangzhujia.foundation.core.dto.polymorphic.PolymorphicDtoAdapter;

@Component
public class CouponsConstrainDtoAdapter extends 
	SimpleDtoAdapter<CouponsConstrainEntity, CouponsConstrainDto> {

	@Autowired
	private List<PolymorphicDtoAdapter<CouponsConstrainEntity, CouponsConstrainDto>> adapters;
	
	public CouponsConstrainDto convert(CouponsConstrainEntity entity) {
		for (PolymorphicDtoAdapter<CouponsConstrainEntity, CouponsConstrainDto> adapter : adapters) {
			if(adapter.support(entity)) {
				CouponsConstrainDto dto=adapter.convert(entity);
				return dto;
			}
		}
		throw new RuntimeException("没有合适的转换器");
	}
	
	public CouponsConstrainEntity convertToDo(CouponsConstrainDto dto) {
		for (PolymorphicDtoAdapter<CouponsConstrainEntity, CouponsConstrainDto> adapter : adapters) {
			if(adapter.supportTarget(dto)) {
				CouponsConstrainEntity entity=adapter.reConvert(dto);
				return entity;
			}
		}
		throw new RuntimeException("没有合适的转换器");
	}

	@Override
	public CouponsConstrainEntity update(CouponsConstrainDto t, CouponsConstrainEntity d) {
		for (PolymorphicDtoAdapter<CouponsConstrainEntity, CouponsConstrainDto> adapter : adapters) {
			if(adapter.supportTarget(t) && adapter.support(d)) {
				d=adapter.update(t, d);
				return d;
			}
		}
		throw new RuntimeException("没有合适的转换器");
	}
}
