package me.chanjar.weixin.pay;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class WxMenuTest {
	
	@Autowired
	private WxMpService service;
	
	@Test
	public void postMenu() {
		WxMenu menu=new WxMenu();
		List<WxMenuButton> buttons=new ArrayList<WxMenu.WxMenuButton>();
		WxMenuButton button1=new WxMenuButton();
		button1.setName("家装套餐");
		WxMenuButton button2=new WxMenuButton();
		button2.setName("家装服务");
		WxMenuButton button3=new WxMenuButton();
		button3.setName("我的");
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		menu.setButtons(buttons);
		//初始化button1
		WxMenuButton button1_1=createButton("view", "套餐预约", "http://123.57.218.171/4g.php?m=u_jztc", null);
		WxMenuButton button1_2=createButton("view", "服务展示", "http://123.57.218.171/4g.php?m=u_fwzs", null);
		WxMenuButton button1_3=createButton("view", "体验馆", "http://123.57.218.171/4g.php?m=u_tyg", null);
		button1.setSubButtons(getButtons(button1_1,button1_2,button1_3));
		//初始化button2
		WxMenuButton button2_1=createButton("view", "凤凰管家", "http://123.57.218.171/4g.php?m=u_fhgj", null);
		WxMenuButton button2_2=createButton("view", "家装贷", "http://123.57.218.171/4g.php?m=u_jzd", null);
		WxMenuButton button2_3=createButton("view", "免费服务预约", "http://123.57.218.171/4g.php?m=u_mffw", null);
		WxMenuButton button2_4=createButton("view", "案例直播", "http://123.57.218.171/4g.php?m=u_anzb", null);
		button2.setSubButtons(getButtons(button2_1,button2_2,button2_3,button2_4));
		//初始化button3
		WxMenuButton button3_1=createButton("view", "会员中心", "http://123.57.218.171/4g.php?m=u_hyzx", null);
		WxMenuButton button3_2=createButton("click", "在线客服", null, "FHZJ_ZXKF");
		WxMenuButton button3_3=createButton("view", "400电话", "tel:4006992888", null);
		WxMenuButton button3_4=createButton("click", "意见反馈", null, "FHZJ_YJFK");
		button3.setSubButtons(getButtons(button3_1,button3_2,button3_3,button3_4));
		try {
			service.menuCreate(menu);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
	
	private WxMenuButton createButton(String type, String name, String url, String key) {
		WxMenuButton button=new WxMenuButton();
		button.setKey(key);
		button.setName(name);
		button.setType(type);
		button.setUrl(url);
		return button;
	}
	
	private List<WxMenuButton> getButtons(WxMenuButton... buttons) {
		List<WxMenuButton> buttonList=new ArrayList<>();
		for (WxMenuButton button : buttons) {
			buttonList.add(button);
		}
		return buttonList;
	}
	
}
