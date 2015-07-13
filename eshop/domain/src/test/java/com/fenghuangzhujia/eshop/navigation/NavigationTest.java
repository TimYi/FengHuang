package com.fenghuangzhujia.eshop.navigation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.view.navigation.Navigation.NavigationType;
import com.fenghuangzhujia.eshop.view.navigation.NavigationService;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationDto;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class NavigationTest {

	@Autowired
	private NavigationService navigationService;
	
	@Test
	public void init() {
		//首页
		addNavigation(0, NavigationType.URL, "首页", "http://101.200.229.135", null);
		//家装套餐
		NavigationDto dto=addNavigation(1, NavigationType.DROPDOWN, "家装套餐", null, null);
		String id=dto.getId();
		addNavigation(0, NavigationType.URL, "699装修套餐", "http://101.200.229.135/tc_699.html?id=8aac48364e2a3809014e2b0e49b20003", id);
		addNavigation(1, NavigationType.URL, "软装套餐", "http://101.200.229.135/tc_799.html?id=8aac48364e62bfa9014e63cee70d0028", id);
		addNavigation(2, NavigationType.URL, "流程展示", "http://101.200.229.135/tmzs.html", id);
		addNavigation(3, NavigationType.URL, "工艺展示", "http://101.200.229.135/gyzs.html", id);
		addNavigation(4, NavigationType.URL, "主材展示", "http://101.200.229.135/zczs.html", id);
		//家装贷
		addNavigation(2, NavigationType.URL, "家装贷", "http://101.200.229.135/jzd.html", null);
		//体验馆
		addNavigation(3, NavigationType.URL, "体验馆", "http://101.200.229.135/tyg.html", null);
		//案例/live直播
		dto=addNavigation(4, NavigationType.DROPDOWN, "案例/live直播", null, null);
		id=dto.getId();
		addNavigation(0, NavigationType.URL, "案例集锦", "http://101.200.229.135/case.html", id);
		addNavigation(0, NavigationType.URL, "家装进行时", "http://101.200.229.135/live.html", id);
		//凤凰管家
		addNavigation(5, NavigationType.URL, "凤凰管家", "http://101.200.229.135/fhgj.html", null);
	}
	
	private NavigationDto addNavigation(int ordernum, NavigationType type, String title, String url, String id) {
		NavigationInputArgs navigation=new NavigationInputArgs();
		navigation.setOrdernum(ordernum);
		navigation.setTitle(title);
		navigation.setType(type);
		navigation.setUrl(url);
		navigation.setSuperNavigationId(id);
		return navigationService.add(navigation);
	}
}
