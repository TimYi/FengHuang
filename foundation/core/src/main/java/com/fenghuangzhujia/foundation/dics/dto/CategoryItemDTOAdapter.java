package com.fenghuangzhujia.foundation.dics.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.Category;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryRepository;

@Component
public class CategoryItemDTOAdapter extends AbstractDtoAdapter<CategoryItem, CategoryItemDto> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryItem convertToDo(CategoryItemDto t) {
		CategoryItem d=new CategoryItem();
		d=update(t, d);
		String type=t.getType();
		Category category=null;
		if(StringUtils.isNotEmpty(type)) {
			category=categoryRepository.getByType(type);
		} else {
			String categoryId=t.getCategoryid();
			if(StringUtils.isNotEmpty(categoryId)) {
				category=categoryRepository.findOne(categoryId);
			}
		}
		d.setCategory(category);
		return d;
	}

	@Override
	public CategoryItem update(CategoryItemDto t, CategoryItem d) {
		d.setAttr(t.getAttr());
		d.setAttr2(t.getAttr2());
		d.setRemark(t.getRemark());
		d.setName(t.getName());
		d.setPriority(t.getPriority());
		return d;
	}

	@Override
	public CategoryItemDto convert(CategoryItem source) {
		CategoryItemDto t=new CategoryItemDto();
		t.setId(source.getId());
		t.setAttr(source.getAttr());
		t.setAttr2(source.getAttr2());
		t.setCategoryid(source.getCategory().getId());
		t.setName(source.getName());
		t.setPriority(source.getPriority());
		t.setRemark(source.getRemark());
		return t;
	}
}
