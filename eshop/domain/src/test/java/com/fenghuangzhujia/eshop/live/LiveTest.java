package com.fenghuangzhujia.eshop.live;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.live.Live.ProjectProgress;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailInputArgs;
import com.fenghuangzhujia.eshop.live.dto.LiveDto;
import com.fenghuangzhujia.eshop.live.dto.LiveInputArgs;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;
import com.fenghuangzhujia.foundation.utils.Java8TimeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class LiveTest {

	@Autowired
	private LiveService liveService;
	@Autowired
	private LiveDetailRepository liveDetailRepository;
	@Autowired
	private LiveDetailService liveDetailService;
	
	@Test
	public void add(){
		for (int i = 0; i < 20; i++) {
			LiveDto live;
			int x=new Random().nextInt()%3;
			if(x<0)x=-x;
			if(x==0) {
				live=addLive("李先生家","碧生源小区","二居室",ProjectProgress.进行中, "8aac48364dd68c74014dd6c031f10000");
			} else if(x==1) {
				live=addLive("易先生家","定福庄小区","一居室",ProjectProgress.已交房, "404040e64d89ca84014d89cb492d0000");
			} else {
				live=addLive("孙小姐","宇宙中心小区","一居室",ProjectProgress.验收中, "404040e64db23347014db2339e450000");
			}
			addDetail(live.getId());
		}
	}
	
	private LiveDto addLive(String name, String village, String house, ProjectProgress status, String userId) {
		LiveInputArgs args=new LiveInputArgs();
		args.setName(name);
		args.setVillage(village);
		args.setArea(88.0);
		args.setHouse(house);
		args.setStartDate(new Date());
		args.setShouldShow(true);
		args.setStatus(status);
		int x=new Random().nextInt()%3;
		if(x<0)x=-x;
		File imgFile=new File("C:/Users/pc/Desktop/live"+x+".jpg");
		MultipartFile mainPicFile=new JpegMultipartFile(imgFile);
		args.setMainPicFile(mainPicFile);
		args.setUserId(userId);
		LiveDto result=liveService.add(args);
		return result;
	}
	
	//@Test
	public void addDetail() {
		for (int i=1;i<=20;i++) {
			addDetail(i,MessageFormat.format("第{0}天", i),"404040e64e1f2e24014e1f2e37cc0001");
		}
	}
	
	private void addDetail(String liveId) {
		for (int i=1;i<=20;i++) {
			addDetail(i,MessageFormat.format("第{0}天", i),liveId);
		}
	}
	
	private LiveDetailDto addDetail(int day, String title, String id) {
		LiveDetailInputArgs args=new LiveDetailInputArgs();
		args.setDay(day);
		LocalDate date=LocalDate.now();
		date=date.plusDays(day);
		Date date2=Java8TimeUtils.fromLocalDate(date);
		args.setDate(date2);
		args.setTitle(title);
		Set<MultipartFile> pics=new HashSet<>();
		for (int i = 0; i < 4; i++) {
			File imgFile=new File("C:/Users/pc/Desktop/test1.jpg");
			MultipartFile pic=new JpegMultipartFile(imgFile);
			pics.add(pic);
		}
		args.setPicFiles(pics);
		Set<MultipartFile> ipics=new HashSet<>();
		for (int i = 0; i < 4; i++) {
			File imgFile=new File("C:/Users/pc/Desktop/test2.jpg");
			MultipartFile pic=new JpegMultipartFile(imgFile);
			ipics.add(pic);
		}
		args.setInteractPicFiles(ipics);
		args.setLiveId(id);
		return liveDetailService.add(args);
	}
	
	//@Test
	public void printLiveInstance() {
		LiveDetail detail=liveDetailRepository.findOne("404040e64e09b62d014e09b640b60000");
		System.out.println(detail);
	}
}
