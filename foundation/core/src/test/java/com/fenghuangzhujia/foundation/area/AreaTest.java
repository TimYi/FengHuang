package com.fenghuangzhujia.foundation.area;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.foundation.area.Area.AreaLevel;
import com.fenghuangzhujia.foundation.area.dto.AreaDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class AreaTest {
	@Autowired
	private AreaService areaService;
	
	@Test
	public void add() {
		AreaDto areaDto=new AreaDto();
		areaDto.setLevel(AreaLevel.CITY);
		areaDto.setName("北京");
		areaService.add(areaDto);
	}
}
