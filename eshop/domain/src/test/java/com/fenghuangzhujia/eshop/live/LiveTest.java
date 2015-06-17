package com.fenghuangzhujia.eshop.live;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.live.dto.LiveDto;
import com.fenghuangzhujia.eshop.live.dto.LiveInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class LiveTest {

	@Autowired
	private LiveService liveService;
	
	@Test
	public void add(){
		LiveInputArgs args=new LiveInputArgs();
		args.setName("test");
		args.setUserId("8aac48364dd68c74014dd6c031f10000");
		LiveDto result=liveService.add(args);
		System.out.println(RequestResult.success(result).toJson());
	}
}
