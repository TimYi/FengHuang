package com.fenghuangzhujia.foundation.dics.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.Category;
import com.fenghuangzhujia.foundation.dics.CategoryRepository;

@Component
public class CategoryDtoAdapter extends AbstractDtoAdapter<Category, CategoryDto, CategoryDto> {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryItemDtoAdapter categoryItemDTOAdapter;

	@Override
	public CategoryDto postConvert(Category d, CategoryDto t) {
		return t;
	}

	@Override
	public Category postConvertToDo(CategoryDto t, Category d) {
		return postUpdate(t, d);
	}

	@Override
	public Category postUpdate(CategoryDto t, Category d) {
		String parentid=t.getParentid();
		Category parent=null;
		if(StringUtils.isNotEmpty(parentid)) {
			parent=categoryRepository.findOne(parentid);
		} else {
			String parentType=t.getParentType();
			if(StringUtils.isNotEmpty(parentType)) {
				parent=categoryRepository.getByType(parentType);
			}
		}
		d.setParent(parent);
		return d;
	}
}
