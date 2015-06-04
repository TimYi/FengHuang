package com.fenghuangzhujia.eshop.collect.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.collect.Collect;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.SimpleDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class CollectDtoAdapter extends SimpleDtoAdapter<Collect, CollectDto> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private List<CollectDtoConverter> converters;
	
	@Override
	public Collect convertToDo(CollectDto t) {
		for (CollectDtoConverter converter : converters) {
			if(converter.support(t)) {
				return converter.convert(t);
			}
		}
		throw new ErrorCodeException(SystemErrorCodes.OTHER,"没有合适的转换器");
	}
	
	@Override
	public Collect update(CollectDto t, Collect d) {
		for (CollectDtoConverter converter : converters) {
			if(converter.support(t)) {
				return converter.update(t, d);
			}
		}
		throw new ErrorCodeException(SystemErrorCodes.OTHER,"没有合适的转换器");
	}
	@Override
	public CollectDto convert(Collect source) {
		return BeanMapper.map(source, CollectDto.class);
	}
}
