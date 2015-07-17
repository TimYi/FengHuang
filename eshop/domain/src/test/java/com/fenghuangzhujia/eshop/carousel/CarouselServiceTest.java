package com.fenghuangzhujia.eshop.carousel;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentInputArgs;
import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.VariableValueInputArgs;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.eshop.view.carousel.CarouselService;
import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselDto;
import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CarouselServiceTest {

	@Autowired
	private CarouselService carouselService;
	
	//@Test
	public void add() {
		CarouselInputArgs args=new CarouselInputArgs();
		args.setOrdernum(0);
		args.setPageId("404040e64e866c94014e866ca4110000");
		TemplateFragmentInputArgs fragment=creaTemplateFragmentInputArgs();
		args.setFragmentInfo(fragment);
		CarouselDto result=carouselService.add(args);
		System.out.println(RequestResult.success(result).toJson());
	}
	
	@Test
	public void update() {
		CarouselInputArgs args=new CarouselInputArgs();
		args.setId("404040e64e99db7e014e99db90e90004");
		args.setOrdernum(0);
		args.setPageId("404040e64e866c94014e866ca4110000");
		TemplateFragmentInputArgs fragment=creaTemplateFragmentInputArgs();
		args.setFragmentInfo(fragment);
		CarouselDto result=carouselService.update(args);
		System.out.println(RequestResult.success(result).toJson());
	}
	
	private TemplateFragmentInputArgs creaTemplateFragmentInputArgs() {
		TemplateFragmentInputArgs args=new TemplateFragmentInputArgs();
		args.setTemplateId("404040e64e94e3ee014e94e4048a0000");
		VariableValueInputArgs variable1=new VariableValueInputArgs();
		variable1.setName("test3");
		variable1.setType(FieldType.TEXT);
		variable1.setText("testText");
		VariableValueInputArgs variable2=new VariableValueInputArgs();
		variable2.setName("test4");
		variable2.setType(FieldType.IMG);
		File file=new File("C:/Users/pc/Desktop/test.jpg");
		JpegMultipartFile picFile=new JpegMultipartFile(file);
		variable2.setPicFile(picFile);
		Set<VariableValueInputArgs> inputArgs=new HashSet<>();
		inputArgs.add(variable1);
		inputArgs.add(variable2);
		args.setValues(inputArgs);
		return args;
	}
}
