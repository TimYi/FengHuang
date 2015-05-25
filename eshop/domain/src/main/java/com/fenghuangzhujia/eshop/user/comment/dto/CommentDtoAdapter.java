package com.fenghuangzhujia.eshop.user.comment.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.user.comment.Comment;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;

@Component
public class CommentDtoAdapter extends AbstractDtoAdapter<Comment, CommentDto> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public CommentDto postConvert(Comment d, CommentDto t) {
		t.setUserid(d.getUser().getId());
		return t;
	}

	@Override
	public Comment postConvertToDo(CommentDto t, Comment d) {
		return postUpdate(t, d);
	}

	@Override
	public Comment postUpdate(CommentDto t, Comment d) {
		String userid=t.getUserid();
		if(StringUtils.isNotBlank(userid)) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		return d;
	}

}
