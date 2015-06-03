package com.fenghuangzhujia.eshop.artical;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.artical.dto.ArticalDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ArticalTest {

	@Autowired
	private ArticalService service;
	
	@Test
	public void add() {
		ArticalDto artical=new ArticalDto();
		artical.setAuthor("Tim");
		artical.setTitle("first artical");
		artical.setContent("test content");
		service.add(artical);
	}
}
