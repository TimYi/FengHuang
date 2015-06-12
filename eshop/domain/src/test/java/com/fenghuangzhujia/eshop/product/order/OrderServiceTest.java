package com.fenghuangzhujia.eshop.product.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrderService;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.utils.JsonSerializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OrderServiceTest {

	@Autowired
	private GoodOrderService service;
	
	@Test
	public void getList() {
		PagedList<GoodOrderDto> result=service.findByStatus(1, 2, null);
		try {
			String pString=JsonSerializer.Serialize(result);
			System.out.println(pString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
