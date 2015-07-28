package com.fenghuangzhujia.eshop.product.packages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.prudoct.packages.space.DecorateSpaceService;
import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateItemDto;
import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateSpaceInputArgs;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DecorateSpaceTest {

	@Autowired
	private DecorateSpaceService service;
	
	@Test
	public void addSpaces() {
		DecorateItemDto item1=createItem("墙面", "东鹏", "不限", "墙面防水处理瓷砖及铺贴辅料（规格300×600mm或300×450，300×300）");
		DecorateItemDto item2=createItem("地面", "东鹏", "不限", "地面防水处理，铺满及铺贴辅料（规格300*300）");
		List<DecorateItemDto> items=createItems(item1,item2);
		addSpace("699", "厨房", 0, "C:/Users/pc/Desktop/1.jpg", "C:/Users/pc/Desktop/2.jpg", "C:/Users/pc/Desktop/3.jpg", items);
		
		item1=createItem("五金", "九牧", "一套", "九牧八件套");
		item2=createItem("洁具", "科勒", "一套", "淋浴花洒、坐便器（含角阀及软管）、台盆、龙头各1套");
		items=createItems(item1,item2);		
		addSpace("599", "卫生间", 1, "C:/Users/pc/Desktop/4.jpg", "C:/Users/pc/Desktop/5.jpg", "C:/Users/pc/Desktop/6.jpg", items);
	}
	
	private DecorateItemDto createItem(String name, String brand, String number, String description) {
		DecorateItemDto item=new DecorateItemDto();
		item.setBrand("东鹏");
		item.setName("地面");
		item.setNumber("不限");
		item.setDescription("地砖（配套踢脚线）");
		return item;
	}
	
	private List<DecorateItemDto> createItems(DecorateItemDto... items) {
		List<DecorateItemDto> itemList=new ArrayList<>();
		for (DecorateItemDto item : items) {
			itemList.add(item);
		}
		return itemList;
	}
	
	private void addSpace(String packageId, String name, int ordernum, String pic1, String pic2, String pic3, 
			List<DecorateItemDto> items) {
		DecorateSpaceInputArgs args=new DecorateSpaceInputArgs();
		args.setDecoratePackageId(packageId);
		args.setName(name);
		args.setOrdernum(0);
		JpegMultipartFile picFile1=new JpegMultipartFile(pic1);
		JpegMultipartFile picFile2=new JpegMultipartFile(pic2);
		JpegMultipartFile picFile3=new JpegMultipartFile(pic3);
		args.setPicFile1(picFile1);
		args.setPicFile2(picFile2);
		args.setPicFile3(picFile3);
		args.setItems(items);		
		service.add(args);
	}
}
