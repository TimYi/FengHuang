package com.fenghuangzhujia.foundation.dics.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.Category;
import com.fenghuangzhujia.foundation.dics.CategoryRepository;

@Component
public class CategoryDTOAdapter extends AbstractDtoAdapter<Category, CategoryDTO> {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryItemDTOAdapter categoryItemDTOAdapter;

	@Override
	public CategoryDTO postConvert(Category d, CategoryDTO t) {
		return t;
	}

	@Override
	public Category postConvertToDo(CategoryDTO t, Category d) {
		return postUpdate(t, d);
	}

	@Override
	public Category postUpdate(CategoryDTO t, Category d) {
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
