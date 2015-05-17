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
	@Test
	public void initServiceType() {
		String name="服务类型";
		String remark="服务类型";
		String type=Dics.IMD_SERVICE_TYPE;
		CategoryDTO t=new CategoryDTO();
		t.setName(name);
		t.setRemark(remark);
		t.setType(type);
		t=categoryService.add(t);
		String[] names = { "pair", "clean", "buy"};
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
