package com.fenghuangzhujia.eshop.product.packages;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DecoratePackageServiceTest {
	
	@Autowired
	private DecoratePackageService service;
	
	@Test
	public void getUserPackages() {
		PagedList result=service.findPage(1, 3, "8aac48364dd68c74014dd6c031f10000");
		System.out.println(RequestResult.success(result).toJson());
	}
	
}
