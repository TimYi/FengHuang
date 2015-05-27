package com.fenghuangzhujia.eshop.product.goods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.prudoct.goods.OrderDecoratePackageService;
import com.fenghuangzhujia.foundation.utils.JsonSerializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OrderServiceTest {

	@Autowired
	private OrderDecoratePackageService service;
	
	//@Test
	public void scramble() {
		String id=service.scramble("404040e64d89ca84014d89cb492d0000", "404040e64d8fa9d4014d8fa9e2e10001",null);
		System.out.println(id);
	}
	
	//@Test
	public void getInfo() {
		Object good=service.getGoodByOrder("404040e64d89ca84014d89cb492d0000","404040e64d940a75014d940a812e0001");
		try {
			System.out.println(JsonSerializer.Serialize(good));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addDetail() {
		String id="404040e64d940a75014d940a812e0001";
		service.addDetail("404040e64d89ca84014d89cb492d0000", id, null, "404040e64d941482014d94148bb70000", "test", 40.0, "test");
	}
}
