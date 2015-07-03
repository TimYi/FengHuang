package com.fenghuangzhujia.eshop.cases;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseInputArgs;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CaseServiceTest {

	@Autowired
	private DecorateCaseService service;
	
	@Test
	public void addCases(){
		for (int i = 0; i < 25; i++) {
			int x=new Random().nextInt()%3;
			if(x==0) {
				addCase("499套餐","美式","厨房","二居室","499套餐 美式 厨房");
			} else if(x==1) {
				addCase("699套餐","古典","客厅","三居室","699套餐 美式 厨房");
			} else {
				addCase("699套餐","古典","客厅","三居室","699套餐 美式 厨房");
			}
		}
	}
	
	private DecorateCaseDto addCase(String packageName, String style, String space, String house, String tags) {
		DecorateCaseInputArgs args=new DecorateCaseInputArgs();
		args.setArea(88.85);
		args.setName("凤凰筑家装修案例");
		args.setPackageName(packageName);
		args.setStyle(style);
		args.setSpace(space);
		args.setHouseType(house);
		args.setDescription("凤凰筑家装修案例");
		args.setCode(CodeGenerater.generateOrderCode());
		args.setPrice(58888.0);
		args.setTagExpression(tags);
		int i=new Random().nextInt()%3;
		if(i<0) {
			i=-i;
		}
		i=i+1;
		File imgFile=new File("C:/Users/pc/Desktop/case"+i+".jpg");
		MultipartFile mainPicFile=new JpegMultipartFile(imgFile);
		args.setMainPicFile(mainPicFile);
		return service.add(args);
	}
	
	//@Test
	public void findByTags() {
		Set<String> tags=new HashSet<>();
		tags.add("美式");
		PagedList<DecorateCaseDto> result=service.findByTags(tags, 1, 4);
		System.out.println(RequestResult.success(result).toJson());
	}
}
