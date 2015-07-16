package com.fenghuangzhujia.eshop.templateEngine;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.templateEngine.fragment.TemplateFragmentService;
import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentDto;
import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentInputArgs;
import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.VariableValueInputArgs;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TemplateFragmentServiceTest {

	@Autowired
	private TemplateFragmentService fragmentService;
	
	//@Test
	public void addFragment() {
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
		TemplateFragmentDto dto=fragmentService.add(args);		
		System.out.println(dto.getTemplateContent());
	}
	
	@Test
	public void updateFragment() {
		TemplateFragmentInputArgs args=new TemplateFragmentInputArgs();
		args.setId("404040e64e966ce8014e966cf9180001");
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
		TemplateFragmentDto dto=fragmentService.add(args);		
		System.out.println(dto.getTemplateContent());
	}
	
}
