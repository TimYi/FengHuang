package com.fenghuangzhujia.eshop.coupons;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.coupons.core.CouponsManager;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsDto;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CouponsTest {

	@Autowired
	private CouponsManager manager;
	@Autowired
	private CouponsService service;
	
	//@Test
	public void allocate() {
		String userid="404040e64d89ca84014d89cb492d0000";
		String defid="404040e64dae78ed014dae78fbd70000";
		manager.allocate(userid, defid);
	}
	
	@Test
	public void getAll() {
		List<CouponsDto> coupons=service.findUnUsedCoupons("404040e64d89ca84014d89cb492d0000");
		RequestResult result=RequestResult.success(coupons);
		System.out.println(result.toJson());
	}
}
