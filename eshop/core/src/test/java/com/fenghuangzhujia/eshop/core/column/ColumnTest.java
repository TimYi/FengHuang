package com.fenghuangzhujia.eshop.core.column;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.menu.MenuService;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ColumnTest {
	@Autowired
	private MenuService columnService;
	
	@Test
	public void add() {
		MenuInputArgs c=new MenuInputArgs();
		c.setName("test");
		c.setTypeid("404040e64d8f6dc0014d8f6dcf520007");
		columnService.add(c);
	}
	
	//@Test
	public void findAll() {
		columnService.findAll();
	}
}
