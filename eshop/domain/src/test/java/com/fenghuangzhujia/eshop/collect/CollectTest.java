package com.fenghuangzhujia.eshop.collect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.eshop.collect.dto.CollectDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CollectTest {
	
	@Autowired
	private CollectService collectService;
	
	@Test
	public void add() {
		collectService.add("404040e64d89ca84014d89cb492d0000", "404040e64d8fa9d4014d8fa9e2e10001", ResourceType.PACKAGE, null);
	}
	
	//@Test
	public void findPage() {
		PagedList<CollectDto> page=collectService.findPage(1, 2, "404040e64d6b6a82014d6b6bd0300000");
		String result=RequestResult.success(page).toJson();
		System.out.println(result);
	}
}
