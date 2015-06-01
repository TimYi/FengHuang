package com.fenghuangzhujia.eshop.coupons;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.coupons.dto.CouponsConstrainDto;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsDefDto;
import com.fenghuangzhujia.eshop.coupons.stragys.quota.QuotaStragyDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CouponsDefTest {
	
	@Autowired
	private CouponsDefService defService;
	
	@Test
	public void add() {
		CouponsDefDto dto=new CouponsDefDto();
		dto.setName("test");
		CouponsConstrainDto constrain=new CouponsConstrainDto();
		constrain.setName("test");
		dto.setConstrain(constrain);
		QuotaStragyDto stragy=new QuotaStragyDto();
		stragy.setName("test");
		stragy.setCouponsMoney(12.0);
		dto.setStragy(stragy);
		defService.add(dto);
	}
}
