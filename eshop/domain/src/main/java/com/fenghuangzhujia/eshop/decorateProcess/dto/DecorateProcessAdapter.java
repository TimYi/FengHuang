package com.fenghuangzhujia.eshop.decorateProcess.dto;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.decorateProcess.DecorateProcess;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class DecorateProcessAdapter extends 
	AbstractDtoAdapter<DecorateProcess, DecorateProcessDto, DecorateProcessInputArgs> {

	@Override
	public DecorateProcessDto postConvert(DecorateProcess d,
			DecorateProcessDto t) {
		return t;
	}

	@Override
	public DecorateProcess postConvertToDo(DecorateProcessInputArgs i,
			DecorateProcess d) {
		return d;
	}

	@Override
	public DecorateProcess postUpdate(DecorateProcessInputArgs i,
			DecorateProcess d) {
		return d;
	}
}
