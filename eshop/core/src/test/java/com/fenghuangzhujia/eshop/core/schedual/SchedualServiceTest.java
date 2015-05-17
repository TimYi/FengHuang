package com.fenghuangzhujia.eshop.core.schedual;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.schedual.activity.Activity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SchedualServiceTest {
	@Autowired
	SchedualService service;
	
	//@Test
	public void add(){
		Schedual schedual=new Schedual();
		service.add(schedual);
	}
	
	@Test
	public void update(){
		List<Operation> operations=new ArrayList<>();
		Operation operation=new Operation();
		operation.setDate(LocalDate.now());
		operation.setStartTime(LocalTime.now().minusMinutes(70));
		operation.setEndTime(LocalTime.now().minusMinutes(60));
		String id="404040e64d57220e014d572212270000";		
		operations.add(operation);
		/*
		operation=new Operation();
		operation.setDelete(true);
		operation.setId("404040e64d2c6ea6014d2c6eaa510000");
		operations.add(operation);*/
		service.update(id, operations);
	}
	
	//@Test
	public void check() {
		List<Activity> result=service.check("404040e64d45fe18014d45fe1bfa0000", LocalDate.now(), LocalDate.now());
		for (Activity activity : result) {
			System.out.println(activity.getStartTime());
		}
	}
}
