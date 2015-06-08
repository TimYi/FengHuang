package com.fenghuangzhujia.foundation.dics.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.Category;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryRepository;

@Component
public class CategoryItemDtoAdapter extends AbstractDtoAdapter<CategoryItem, CategoryItemDto, CategoryItemDto> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryItemDto postConvert(CategoryItem d, CategoryItemDto t) {
		t.setCategoryid(d.getCategory().getId());
		return t;
	}

	@Override
	public CategoryItem postConvertToDo(CategoryItemDto t, CategoryItem d) {
		return postUpdate(t, d);
	}

	@Override
	public CategoryItem postUpdate(CategoryItemDto t, CategoryItem d) {
		String type=t.getType();
		String categoryid=t.getCategoryid();
		Category category=null;
		if(StringUtils.isNotEmpty(type)) {
			category=categoryRepository.getByType(type);
		} else {
			if(StringUtils.isNotEmpty(categoryid)) {
				category=categoryRepository.findOne(categoryid);
			}
		}
		d.setCategory(category);
		return d;
	}
	
}
