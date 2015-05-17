package com.fenghuangzhujia.foundation.dics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.dics.dto.CategoryDTO;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class DicsTest {
	
	@Autowired
	private CategoryService service;
	@Autowired
	private CategoryItemService itemService;

	//@Test
	public void addCategory() {
		CategoryDTO category=new CategoryDTO();
		category.setName("test3");
		category.setType("test3");
		category=service.add(category);
		System.out.println(category.getId());
	}
	
	@Test
	public void deleteByType() {
		service.deleteByType("test3");
	}
	
	//@Test
	public void getCategories() {
		PagedList<CategoryDTO> page=service.findPage(1, 2);
		System.out.println(page.getTotalCount());
	}
	
	//@Test
	public void addItem(){
		CategoryItemDto item=new CategoryItemDto();
		item.setName("test");
		item.setType("test");
		itemService.add(item);
	}
}

