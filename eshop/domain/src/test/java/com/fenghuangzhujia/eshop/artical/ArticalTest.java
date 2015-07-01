package com.fenghuangzhujia.eshop.artical;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.artical.dto.ArticalInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ArticalTest {

	@Autowired
	private ArticalService service;
	
	@Test
	public void addInitData() {
		addArtical("服务指南");
		addArtical("支付方式");
		addArtical("常见问题");
		addArtical("售后政策");
		addArtical("自助服务");
		addArtical("相关下载");
		addArtical("会员俱乐部");
		addArtical("会员活动");
		addArtical("VIP服务");
		addArtical("联系我们");
		addArtical("了解我们");
		addArtical("加入我们");
	}
	
	private void addArtical(String code) {
		addArtical(code, code, code+"内容");
	}
	
	private void addArtical(String code, String title, String content) {
		ArticalInputArgs artical=new ArticalInputArgs();
		artical.setAuthor("Tim");
		artical.setCode(code);
		artical.setTitle(title);
		artical.setContent(content);
		service.add(artical);
	}
}
