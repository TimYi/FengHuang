package com.fenghuangzhujia.eshop.core.column.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.column.Column;
import com.fenghuangzhujia.eshop.core.column.ColumnRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Component
public class ColumDtoAdapter extends AbstractDtoAdapter<Column, ColumnDto> {
	
	@Autowired
	private ColumnRepository columnRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;

	@Override
	public Column convertToDo(ColumnDto t) {
		if(t==null)return null;
		Column column=BeanMapper.map(t, Column.class);
		String fatherid=t.getFatherid();
		if(fatherid!=null) {
			Column father=columnRepository.findOne(fatherid);
			column.setFather(father);
		}
		String typeid=t.getTypeid();
		if(typeid!=null) {
			CategoryItem type=categoryItemRepository.findOne(typeid);
			column.setType(type);
		}
		return column;
	}

	@Override
	public Column update(ColumnDto t, Column d) {
		if(t==null||d==null)return null;
		BeanMapper.copy(t, d);
		String fatherid=t.getFatherid();
		if(fatherid!=null) {
			Column father=columnRepository.findOne(fatherid);
			d.setFather(father);
		}
		String typeid=t.getTypeid();
		if(typeid!=null) {
			CategoryItem type=categoryItemRepository.findOne(typeid);
			d.setType(type);
		}
		return d;
	}

	@Override
	public ColumnDto convert(Column source) {
		if(source==null)return null;
		ColumnDto t=BeanMapper.map(source, ColumnDto.class);
		return t;
	}

}
