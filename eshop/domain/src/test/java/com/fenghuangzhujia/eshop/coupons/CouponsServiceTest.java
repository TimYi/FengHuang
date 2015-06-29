package com.fenghuangzhujia.eshop.coupons;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.commerce.coupons.CouponsService;
import com.fenghuangzhujia.eshop.core.commerce.coupons.dto.CouponsInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CouponsServiceTest {
	
	@Autowired
	private CouponsService couponsService;

	@Test
	public void addCoupons() {
		CouponsInputArgs args=new CouponsInputArgs();
		args.setCouponsMoney(100.0);
		args.setExpireTime(new Date());
		args.setName("测试优惠券");
		args.setUserId("8aac48364dd68c74014dd6c031f10000");		
		couponsService.add(args);
	}
}
