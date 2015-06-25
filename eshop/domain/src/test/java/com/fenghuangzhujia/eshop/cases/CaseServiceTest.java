package com.fenghuangzhujia.eshop.cases;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseInputArgs;
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
		for (int i = 0; i < 20; i++) {
			addCase();
		}
	}
	
	private DecorateCaseDto addCase() {
		DecorateCaseInputArgs args=new DecorateCaseInputArgs();
		args.setArea(88.85);
		args.setName("凤凰筑家装修案例");
		args.setPackageName("499套餐");
		args.setStyle("美式");
		args.setSpace("厨房");
		args.setHouseType("二居室");
		args.setDescription("凤凰筑家装修案例");
		args.setCode("XFD11224512");
		args.setPrice(58888.0);
		args.setTagExpression("499套餐 美式 厨房");
		File imgFile=new File("C:/Users/pc/Desktop/test.jpg");
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
