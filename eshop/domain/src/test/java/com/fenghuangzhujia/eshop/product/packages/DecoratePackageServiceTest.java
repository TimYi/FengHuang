package com.fenghuangzhujia.eshop.product.packages;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DecoratePackageServiceTest {
	
	@Autowired
	private DecoratePackageService service;
	
}
