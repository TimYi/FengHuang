package com.fenghuangzhujia.eshop.core.dics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.base.Dics;
import com.fenghuangzhujia.foundation.dics.CategoryItemService;
import com.fenghuangzhujia.foundation.dics.CategoryService;
import com.fenghuangzhujia.foundation.dics.dto.CategoryDTO;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DicsInitializer {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryItemService categoryItemService;
	
	/**
	 * 高级定制字典类
	 */
	public void initServiceType() {
		String name="预约类型";
		String remark="预约类型";
		String type=Dics.APPOINT_TYPE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "validate", "measure", "design"};
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
	
	@Test
	public void initColumnType() {
		String name="栏目类型";
		String remark="栏目类型";
		String type=Dics.COLUMN_TYPE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "product", "artical" };
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
	
	@Test
	public void initHouseType() {
		String name="房屋类型";
		String remark="房屋类型";
		String type=Dics.HOUSE_TYPE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "新房", "旧房" };
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
	
	@Test
	public void initDecorateType() {
		String name="装修类型";
		String remark="装修类型";
		String type=Dics.DECORATE_TYPE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "旧房到精装", "新房到精装" };
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
	
	@Test
	public void initResponseType() {
		String name="包装类型";
		String remark="包装类型";
		String type=Dics.RESPONSE_TYPE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "全包", "半包" };
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
	
	@Test
	public void initCategory() {
		String name="类型";
		String remark="类型";
		String type=Dics.CATEGORY;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "客厅", "厨房" };
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
	
	@Test
	public void initStyle() {
		String name="风格";
		String remark="风格";
		String type=Dics.STYLE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "美式", "欧式" };
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
	
	@Test
	public void initApartment() {
		String name="户型";
		String remark="户型";
		String type=Dics.APARTMENT_TYPE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "三居室", "两居室" };
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
}
