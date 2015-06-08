package com.fenghuangzhujia.foundation.area.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.area.Area;
import com.fenghuangzhujia.foundation.area.AreaRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class AreaAdapter extends AbstractDtoAdapter<Area, AreaDto, AreaDto> {
	
	@Autowired
	private AreaRepository repository;

	@Override
	public AreaDto postConvert(Area d, AreaDto t) {
		return t;		
	}

	@Override
	public Area postConvertToDo(AreaDto t, Area d) {
		return postUpdate(t, d);
	}

	@Override
	public Area postUpdate(AreaDto t, Area d) {
		String upperAreaId=t.getUpperAreaId();
		if(StringUtils.isNotBlank(upperAreaId)){
			Area upperArea=repository.findOne(upperAreaId);
			d.setUpperArea(upperArea);
		}
		return d;
	}	
}
