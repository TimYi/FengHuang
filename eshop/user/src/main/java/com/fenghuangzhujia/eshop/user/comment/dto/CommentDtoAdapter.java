package com.fenghuangzhujia.eshop.user.comment.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.user.comment.Comment;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Component
public class CommentDtoAdapter extends AbstractDtoAdapter<Comment, CommentDto> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Comment convertToDo(CommentDto t) {
		if(t==null)return null;
		Comment d=BeanMapper.map(t, Comment.class);
		if(t.getUserid()!=null) {
			User user=userRepository.findOne(t.getUserid());
			d.setUser(user);
		}
		return d;
	}

	@Override
	public Comment update(CommentDto t, Comment d) {
		if(t==null||d==null)return null;
		BeanMapper.copy(t, d);
		if(t.getUserid()!=null) {
			User user=userRepository.findOne(t.getUserid());
			d.setUser(user);
		}
		return d;
	}

	@Override
	public CommentDto convert(Comment source) {
		if(source==null)return null;
		CommentDto t=BeanMapper.map(source, CommentDto.class);
		t.setUserid(source.getUser().getId());
		return t;
	}

}
