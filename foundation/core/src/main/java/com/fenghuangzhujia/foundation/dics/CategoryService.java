package com.fenghuangzhujia.foundation.dics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.dics.dto.CategoryDto;

@Service
@Transactional
public class CategoryService extends DtoPagingService<Category, CategoryDto, CategoryDto, String> {

	@Autowired
	public void setCategoryRepository(CategoryRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public CategoryRepository getRepository() {
		return (CategoryRepository)super.getRepository();
	}
	
	/**
	 * 根据类别名称获取字典信息
	 * @param type
	 * @return
	 */
	public CategoryDto getByType(String type) {
		Category d=getRepository().getByType(type);
		CategoryDto t=adapter.convert(d);
		return t;
	}
	
	public void deleteByType(String type) {
		if(type==null)return;
		getRepository().deleteByType(type);
	}
}
