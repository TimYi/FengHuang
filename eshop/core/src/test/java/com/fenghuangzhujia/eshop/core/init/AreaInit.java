package com.fenghuangzhujia.eshop.core.init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.core.area.AreaService;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.area.dto.AreaInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AreaInit {

	@Autowired
	private AreaService areaService;
	
	@Test
	public void init() {
		AreaInputArgs areaDto=new AreaInputArgs();
		areaDto.setName("朝阳");
		areaDto.setLevel(AreaLevel.AREA);
		areaService.add(areaDto);
	}
}
