package com.fenghuangzhujia.eshop.core.column.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.column.Column;
import com.fenghuangzhujia.eshop.core.column.ColumnRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Component
public class ColumDtoAdapter extends AbstractDtoAdapter<Column, ColumnDto> {
	
	@Autowired
	private ColumnRepository columnRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;

	@Override
	public ColumnDto postConvert(Column d, ColumnDto t) {
		return t;
	}

	@Override
	public Column postConvertToDo(ColumnDto t, Column d) {
		return postUpdate(t, d);
	}

	@Override
	public Column postUpdate(ColumnDto t, Column d) {
		String fatherid=t.getFatherid();
		if(StringUtils.isNotBlank(fatherid)) {
			Column father=columnRepository.findOne(fatherid);
			d.setFather(father);
		}
		String typeid=t.getTypeid();
		if(StringUtils.isNotBlank(typeid)) {
			CategoryItem type=categoryItemRepository.findOne(typeid);
			d.setType(type);
		}
		return d;
	}

}
