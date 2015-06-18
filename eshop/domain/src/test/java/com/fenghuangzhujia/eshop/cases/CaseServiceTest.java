package com.fenghuangzhujia.eshop.cases;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CaseServiceTest {

	@Autowired
	private DecorateCaseService service;
	
	
	public void addCase(){
		DecorateCaseInputArgs args=new DecorateCaseInputArgs();
		args.setArea(88.85);
		args.setName("凤凰筑家装修案例");
		args.setStyle("美式");
		args.setPrice(58888.0);
		args.setTagExpression("499套餐 美式 厨房");
		service.add(args);
	}
	
	@Test
	public void findByTags() {
		Set<String> tags=new HashSet<>();
		tags.add("美式");
		PagedList<DecorateCaseDto> result=service.findByTags(tags, 1, 4);
		System.out.println(RequestResult.success(result).toJson());
	}
}
