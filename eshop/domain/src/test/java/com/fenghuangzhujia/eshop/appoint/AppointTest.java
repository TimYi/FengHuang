package com.fenghuangzhujia.eshop.appoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.appoint.dto.AppointInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AppointTest {
	@Autowired
	private AppointService appointService;
	
	@Test
	public void add() {
		AppointInputArgs appointDto=new AppointInputArgs();
		appointDto.setAddress("test");
		appointDto.setUserid("404040e64d6b19f3014d6b1a52ad0000");
		appointDto.setMobile("18612444099");
		appointDto.setTypeid("404040e64d7962ad014d7962b3240003");
		appointService.add(appointDto);
	}
}
