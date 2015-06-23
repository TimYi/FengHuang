package com.fenghuangzhujia.foundation.media;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class MediasTest {
	
	@Autowired
	private MediaService service;
	
	@Test
	public void addMedia() {
		File imgFile=new File("C:/Users/pc/Desktop/test.jpg");
		MultipartFile file=new JpegMultipartFile(imgFile);
		try {
			MediaContent media=service.save(file);
			System.out.println(media.getUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	//@Test
	public void getMedia() {
		MediaContent media=service.getMedia("404040e64d4206d9014d4206dd1b0000");
		System.out.println(media.getUrl());
	}
}

