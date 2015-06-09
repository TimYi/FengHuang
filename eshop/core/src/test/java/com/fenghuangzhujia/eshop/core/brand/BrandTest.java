package com.fenghuangzhujia.eshop.core.brand;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.eshop.core.brand.dto.BrandTypeInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BrandTest {

	@Autowired
	private BrandService brandService;
	@Autowired
	private BrandTypeService brandTypeService;
	
	//@Test
	public void addType() {
		BrandTypeInputArgs dto=new BrandTypeInputArgs();
		dto.setName("test");
		brandTypeService.add(dto);
	}
	
	@Test
	public void add() {
		BrandInputArgs dto=new BrandInputArgs();
		dto.setName("test");
		dto.setBrandTypeId("404040e64d8f761f014d8f76293d0000");
		brandService.add(dto);
	}
}
