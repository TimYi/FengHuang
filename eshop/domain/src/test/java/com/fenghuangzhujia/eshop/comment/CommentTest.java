package com.fenghuangzhujia.eshop.comment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.comment.CommentItemService;
import com.fenghuangzhujia.eshop.comment.CommentService;
import com.fenghuangzhujia.eshop.comment.dto.CommentDto;
import com.fenghuangzhujia.eshop.comment.dto.CommentItemInputArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CommentTest {

	@Autowired
	private CommentItemService commentItemService;
	@Autowired
	private CommentService commentService;
	
	//@Test
	public void addComment() {
		CommentDto comment=new CommentDto();
		comment.setSourceid("123456");
		comment=commentService.add(comment);
		System.out.println(comment.getId());
	}
	
	//@Test
	public void addItem() {
		CommentItemInputArgs comment=new CommentItemInputArgs();
		comment.setUserid("404040e64d89ca84014d89cb492d0000");
		comment.setSourceid("123456");
		commentItemService.add(comment);
	}
	
	@Test
	public void getUnReaded() {
		Long count=commentItemService.countByIsReaded("8aac48364dd68c74014dd6c031f10000", false);
		System.out.println(count);
	}
}
