package com.fenghuangzhujia.foundation.area.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.area.Area;
import com.fenghuangzhujia.foundation.area.AreaRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Component
public class AreaAdapter extends AbstractDtoAdapter<Area, AreaDto> {
	
	@Autowired
	private AreaRepository repository;
	
	@Override
	public Area convertToDo(AreaDto t) {
		Area d=BeanMapper.map(t, Area.class);
		String upperAreaId=t.getUpperAreaId();
		if(upperAreaId!=null){
			Area upperArea=repository.findOne(upperAreaId);
			d.setUpperArea(upperArea);
		}
		return d;
	}

	@Override
	public Area update(AreaDto t, Area d) {
		BeanMapper.copy(t, d);
		String upperAreaId=t.getUpperAreaId();
		if(upperAreaId!=null){
			Area upperArea=repository.findOne(upperAreaId);
			d.setUpperArea(upperArea);
		}
		return d;
	}

	@Override
	public AreaDto convert(Area source) {
		AreaDto t=BeanMapper.map(source, AreaDto.class);
		return t;
	}
}
