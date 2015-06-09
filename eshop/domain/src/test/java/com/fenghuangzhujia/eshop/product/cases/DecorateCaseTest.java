package com.fenghuangzhujia.eshop.product.cases;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCaseService;
import com.fenghuangzhujia.eshop.prudoct.cases.dto.DecorateCaseInputArgs;
import com.fenghuangzhujia.eshop.prudoct.detail.DecorateDetail;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DecorateCaseTest {

	@Autowired
	private DecorateCaseService service;
	
	//@Test
	public void add() {
		DecorateCaseInputArgs dto=new DecorateCaseInputArgs();
		dto.setBrandid("404040e64d8f7710014d8f771afb0000");
		dto.setMenuid("404040e64d8f7405014d8f740fb40000");
		dto.setTitle("test");
		dto.setApartmentTypeId("404040e64d8f6dc0014d8f6dd137000a");
		DecorateDetail detail=new DecorateDetail();
		detail.setOrdernum(1);
		detail.setCount("不限");
		detail.setMeterial("白银");
		Set<DecorateDetail> details=new HashSet<>();
		details.add(detail);
		dto.setDetails(details);
		File file=new File("C:/Users/pc/Desktop/预约流程.png");
		MultipartFile mainPicFile=new JpegMultipartFile(file);
		dto.setMainPicFile(mainPicFile);		
		MultipartFile[] picFiles={mainPicFile};
		dto.setPicFiles(picFiles);
		service.add(dto);
	}
	
	//@Test
	public void addFile() {
		File file=new File("C:/Users/pc/Desktop/预约流程.png");
		MultipartFile picFile=new JpegMultipartFile(file);
		service.addPic("404040e64d8fcb55014d8fcb65760001", picFile);
	}
	
	@Test
	public void update() {
		DecorateCaseInputArgs dto=new DecorateCaseInputArgs();
		dto.setId("404040e64d8fcb55014d8fcb65760001");
		dto.setBrandid("404040e64d8f7710014d8f771afb0000");
		dto.setMenuid("404040e64d8f7405014d8f740fb40000");
		dto.setTitle("test");
		dto.setApartmentTypeId("404040e64d8f6dc0014d8f6dd137000a");
		DecorateDetail detail=new DecorateDetail();
		detail.setOrdernum(1);
		detail.setCount("不限");
		detail.setMeterial("白银");
		Set<DecorateDetail> details=new HashSet<>();
		details.add(detail);
		dto.setDetails(details);
		File file=new File("C:/Users/pc/Desktop/预约流程.png");
		MultipartFile mainPicFile=new JpegMultipartFile(file);
		dto.setMainPicFile(mainPicFile);
		service.update(dto);
	}
}
