package com.fenghuangzhujia.eshop.product.packages;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.prudoct.detail.DecorateDetail;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageService;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageInputArgs;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DecoratePackageServiceTest {
	
	@Autowired
	private DecoratePackageService service;
	
	//@Test
	public void add() {
		DecoratePackageInputArgs dto=new DecoratePackageInputArgs();
		dto.setBrandid("404040e64d8f7710014d8f771afb0000");
		dto.setMenuid("404040e64d8f7405014d8f740fb40000");
		dto.setContent("test");
		dto.setDecorateTypeId("404040e64d8f6dc0014d8f6dd8fa0014");
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
		service.add(dto);
	}
	
	@Test
	public void update() {
		DecoratePackageInputArgs dto=new DecoratePackageInputArgs();
		dto.setId("404040e64d8fa9d4014d8fa9e2e10001");
		dto.setBrandid("404040e64d8f7710014d8f771afb0000");
		dto.setMenuid("404040e64d8f7405014d8f740fb40000");
		dto.setContent("test1");
		dto.setDecorateTypeId("404040e64d8f6dc0014d8f6dd8fa0014");
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
