package com.fenghuangzhujia.foundation.dics.dto;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.Category;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryRepository;

@Component
public class CategoryDTOAdapter extends AbstractDtoAdapter<Category, CategoryDTO> {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryItemDTOAdapter categoryItemDTOAdapter;
	
	
	@Override
	public Category convertToDo(CategoryDTO t) {
		Category d=new Category();
		d = update(t, d);
		return d;
	}
	
	@Override
	public Category update(CategoryDTO t, Category d) {
		d.setName(t.getName());
		d.setRemark(t.getRemark());
		d.setType(t.getType());
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
	
	@Override
	public CategoryDTO convert(Category d) {
		CategoryDTO t=new CategoryDTO();
		t.setId(d.getId());
		t.setName(d.getName());
		t.setRemark(d.getRemark());
		t.setType(d.getType());
		Category parent=d.getParent();
		String parentid=parent==null?null:parent.getId();
		t.setParentid(parentid);
		Set<CategoryItem> items=d.getItems();
		if(items!=null) {
			Set<CategoryItemDto> tItems=new HashSet<CategoryItemDto>();
			tItems.addAll(categoryItemDTOAdapter.convertDoList(items));
			t.setItems(tItems);
		}		
		Set<Category> subCategorys=d.getSubCategories();
		if(subCategorys!=null) {
			Set<CategoryDTO> subCategoryDTOs=new HashSet<CategoryDTO>();
			subCategoryDTOs.addAll(convertDoList(subCategorys));
			t.setSubCategories(subCategoryDTOs);
		}		
		return t;
	}
}
