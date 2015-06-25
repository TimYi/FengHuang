package com.fenghuangzhujia.eshop.dics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.area.AreaService;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.area.dto.AreaInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AreaInitialezer {

	@Autowired
	private AreaService areaService;
	
	@Test
	public void initProvAndCity() {
		String hebeiId=addProv("河北").getId();
		addCity("保定", hebeiId);
		addCity("秦皇岛", hebeiId);
		String beijingId=addProv("北京").getId();
		addCity("北京", beijingId);
	}
	
	private AreaDto addProv(String name) {
		AreaInputArgs args=new AreaInputArgs();
		args.setLevel(AreaLevel.PROV);
		args.setName(name);
		AreaDto area=areaService.add(args);
		return area;
	}
	
	private AreaDto addCity(String name, String provid) {
		AreaInputArgs args=new AreaInputArgs();
		args.setLevel(AreaLevel.CITY);
		args.setName(name);
		args.setUpperAreaId(provid);
		AreaDto area=areaService.add(args);
		return area;
	}
}
