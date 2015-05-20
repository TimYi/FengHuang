package com.fenghuangzhujia.eshop.user.collect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.user.collect.CollectService;
import com.fenghuangzhujia.eshop.user.collect.dto.CollectDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CollectTest {
	
	@Autowired
	private CollectService collectService;
	
	
	public void add() {
		CollectDto collect=new CollectDto();
		collect.setName("test");
		collect.setUserid("404040e64d6b19f3014d6b1a52ad0000");
		collectService.add(collect);
	}
	
	@Test
	public void findPage() {
		PagedList<CollectDto> page=collectService.findPage(1, 2, "404040e64d6b6a82014d6b6bd0300000");
		System.out.println(page.getTotalCount());
	}
}
