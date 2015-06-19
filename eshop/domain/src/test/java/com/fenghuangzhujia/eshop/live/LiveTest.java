package com.fenghuangzhujia.eshop.live;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.live.dto.LiveDetailInputArgs;
import com.fenghuangzhujia.eshop.live.dto.LiveDto;
import com.fenghuangzhujia.eshop.live.dto.LiveInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class LiveTest {

	@Autowired
	private LiveService liveService;
	@Autowired
	private LiveDetailRepository liveDetailRepository;
	@Autowired
	private LiveDetailService liveDetailService;
	
	//@Test
	public void add(){
		LiveInputArgs args=new LiveInputArgs();
		args.setName("test");
		args.setUserId("8aac48364dd68c74014dd6c031f10000");
		LiveDto result=liveService.add(args);
		System.out.println(RequestResult.success(result).toJson());
	}
	
	//@Test
	public void addDetail() {
		LiveDetailInputArgs args=new LiveDetailInputArgs();
		args.setDay(1);
		args.setDate(new Date());
		args.setTitle("test");
		args.setLiveId("404040e64e09b411014e09b422f30000");
		liveDetailService.add(args);
	}
	
	@Test
	public void printLiveInstance() {
		LiveDetail detail=liveDetailRepository.findOne("404040e64e09b62d014e09b640b60000");
		System.out.println(detail);
	}
}
