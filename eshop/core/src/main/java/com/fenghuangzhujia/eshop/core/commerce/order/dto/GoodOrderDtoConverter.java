package com.fenghuangzhujia.eshop.core.commerce.order.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Component
public class GoodOrderDtoConverter implements Converter<GoodOrder, GoodOrderDto> {

	@Autowired
	private List<GoodConverter> converters;
	
	@Override
	public GoodOrderDto convert(GoodOrder source) {
		GoodOrderDto dto=BeanMapper.map(source, GoodOrderDto.class);
		if(source.getShop()!=null) {
			dto.setShopid(source.getShop().getId());
			dto.setShop(source.getShop().getName());
		}
		for (GoodConverter converter : converters) {
			if(converter.support(source.getGood())) {
				Object good=converter.convert(source.getGood());
				dto.setGood(good);
				return dto;
			}
		}
		throw new ErrorCodeException(SystemErrorCodes.GOOD_CONVERTER_MISSING);
	}
}
