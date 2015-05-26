package com.fenghuangzhujia.eshop.core.column;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.menu.MenuService;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ColumnTest {
	@Autowired
	private MenuService columnService;
	
	@Test
	public void add() {
		MenuDto c=new MenuDto();
		c.setName("test");
		c.setTypeid("404040e64d8f6dc0014d8f6dcf520007");
		columnService.add(c);
	}
	
	//@Test
	public void findByTypeName() {
		List<MenuDto> result=columnService.findByTypeName("artical");
		System.out.println(result.size());
	}
	
	//@Test
	public void findAll() {
		columnService.findAll();
	}
}
