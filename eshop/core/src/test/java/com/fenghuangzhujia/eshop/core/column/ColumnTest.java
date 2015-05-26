package com.fenghuangzhujia.eshop.core.column;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.column.dto.ColumnDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ColumnTest {
	@Autowired
	private ColumnService columnService;
	
	
	public void add() {
		ColumnDto c=new ColumnDto();
		c.setName("test");
		c.setTypeid("404040e64d7a697c014d7a69866a0001");
		columnService.add(c);
	}
	
	@Test
	public void findByTypeName() {
		List<ColumnDto> result=columnService.findByTypeName("artical");
		System.out.println(result.size());
	}
	
	//@Test
	public void findAll() {
		columnService.findAll();
	}
}
