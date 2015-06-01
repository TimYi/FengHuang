package com.fenghuangzhujia.eshop.coupons.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.coupons.entity.CouponsStragyEntity;
import com.fenghuangzhujia.foundation.core.dto.SimpleDtoAdapter;
import com.fenghuangzhujia.foundation.core.dto.polymorphic.PolymorphicDtoAdapter;

@Component
public class CouponsStragyDtoAdapter extends 
	SimpleDtoAdapter<CouponsStragyEntity, CouponsStragyDto> {
	
	@Autowired
	private List<PolymorphicDtoAdapter<CouponsStragyEntity, CouponsStragyDto>> adapters;

	public CouponsStragyDto convert(CouponsStragyEntity entity) {
		for (PolymorphicDtoAdapter<CouponsStragyEntity, CouponsStragyDto> adapter : adapters) {
			if(adapter.support(entity)) {
				CouponsStragyDto dto=adapter.convert(entity);
				return dto;
			}
		}
		throw new RuntimeException("没有合适的转换器");
	}
	
	public CouponsStragyEntity convertToDo(CouponsStragyDto dto) {
		for (PolymorphicDtoAdapter<CouponsStragyEntity, CouponsStragyDto> adapter : adapters) {
			if(adapter.supportTarget(dto)) {
				CouponsStragyEntity entity=adapter.reConvert(dto);
				return entity;
			}
		}
		throw new RuntimeException("没有合适的转换器");
	}

	@Override
	public CouponsStragyEntity update(CouponsStragyDto t, CouponsStragyEntity d) {
		for (PolymorphicDtoAdapter<CouponsStragyEntity, CouponsStragyDto> adapter : adapters) {
			if(adapter.supportTarget(t) && adapter.support(d)) {
				d=adapter.update(t, d);
				return d;
			}
		}
		throw new RuntimeException("没有合适的转换器");
	}
}
