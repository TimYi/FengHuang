package com.fenghuangzhujia.eshop.dics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.base.Dics;
import com.fenghuangzhujia.foundation.dics.CategoryItemService;
import com.fenghuangzhujia.foundation.dics.CategoryService;
import com.fenghuangzhujia.foundation.dics.dto.CategoryDto;
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
		CategoryDto t=new CategoryDto();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "验房", "量房", "设计"};
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
	
	//@Test
	public void initColumnType() {
		String name="栏目类型";
		String remark="栏目类型";
		String type=Dics.COLUMN_TYPE;
		CategoryDto t=new CategoryDto();
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
	
	//@Test
	public void initHouseType() {
		String name="房屋类型";
		String remark="房屋类型";
		String type=Dics.HOUSE_TYPE;
		CategoryDto t=new CategoryDto();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "公寓", "复式", "别墅" };
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
	
	//@Test
	public void initDecorateType() {
		String name="装修类型";
		String remark="装修类型";
		String type=Dics.DECORATE_TYPE;
		CategoryDto t=new CategoryDto();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "精装", "简装", "旧房改造" };
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
	
	@Deprecated
	public void initResponseType() {
		String name="包装类型";
		String remark="包装类型";
		String type=Dics.RESPONSE_TYPE;
		CategoryDto t=new CategoryDto();
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
	
	@Deprecated
	public void initCategory() {
		String name="类型";
		String remark="类型";
		String type=Dics.CATEGORY;
		CategoryDto t=new CategoryDto();
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
	
	@Deprecated
	public void initStyle() {
		String name="风格";
		String remark="风格";
		String type=Dics.STYLE;
		CategoryDto t=new CategoryDto();
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
	
	@Deprecated
	public void initApartment() {
		String name="户型";
		String remark="户型";
		String type=Dics.APARTMENT_TYPE;
		CategoryDto t=new CategoryDto();
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
	
	//@Test
	public void initSex() {
		String name="性别";
		String remark="性别";
		String type=Dics.SEX;
		CategoryDto t=new CategoryDto();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "男", "女" };
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
	
	//@Test
	public void initBloodType() {
		String name="血型";
		String remark="血型";
		String type=Dics.BLOOD;
		CategoryDto t=new CategoryDto();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "A", "B","O","AB" };
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
	
	//@Test
	public void initConstellation() {
		String name="星座";
		String remark="星座";
		String type=Dics.CONSTELLATION;
		CategoryDto t=new CategoryDto();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "水瓶座", "双鱼座","白羊座","金牛座","双子座", "巨蟹座","狮子座","处女座","天枰座", "天蝎座","射手座","摩羯座" };
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
	public void initColors() {
		String name="颜色";
		String remark="通用颜色";
		String type=Dics.COLOR;
		CategoryDto t=new CategoryDto();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "黑色", "白色" };
		String[] colors = { "#000000", "#FFFFFF"};
		for (int i = 0; i < names.length; i++) {
			name=names[i];
			String attr=colors[i];
			Integer priority=i+1;
			CategoryItemDto item=new CategoryItemDto();
			item.setType(type);
			item.setName(name);
			item.setAttr(attr);
			item.setPriority(priority);
			categoryItemService.add(item);
		}
	}
}
