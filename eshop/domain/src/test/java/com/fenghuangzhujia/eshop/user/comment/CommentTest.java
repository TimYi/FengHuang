package com.fenghuangzhujia.eshop.user.comment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.user.comment.dto.CommentDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CommentTest {

	@Autowired
	private CommentService commentService;
	
	@Test
	public void add() {
		CommentDto comment=new CommentDto();
		comment.setUserid("404040e64d6b19f3014d6b1a52ad0000");
		comment.setMessage("test");
		commentService.add(comment);
	}
}
