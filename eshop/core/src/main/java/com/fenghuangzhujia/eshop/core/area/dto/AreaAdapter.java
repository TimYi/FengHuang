package com.fenghuangzhujia.eshop.core.area.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class AreaAdapter extends AbstractDtoAdapter<Area, AreaDto, AreaInputArgs> {
	
	@Autowired
	private AreaRepository repository;

	@Override
	public AreaDto postConvert(Area d, AreaDto t) {
		return t;		
	}

	@Override
	public Area postConvertToDo(AreaInputArgs t, Area d) {
		return postUpdate(t, d);
	}

	@Override
	public Area postUpdate(AreaInputArgs t, Area d) {
		String upperAreaId=t.getUpperAreaId();
		if(StringUtils.isNotBlank(upperAreaId)){
			Area upperArea=repository.findOne(upperAreaId);
			d.setUpperArea(upperArea);
		}
		return d;
	}	
}
