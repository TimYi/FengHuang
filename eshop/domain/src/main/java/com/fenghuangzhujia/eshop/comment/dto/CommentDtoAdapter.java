package com.fenghuangzhujia.eshop.comment.dto;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.comment.Comment;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class CommentDtoAdapter extends AbstractDtoAdapter<Comment, CommentDto, CommentDto> {
	@Override
	public CommentDto postConvert(Comment d, CommentDto t) {
		return t;
	}

	@Override
	public Comment postConvertToDo(CommentDto t, Comment d) {
		return d;
	}

	@Override
	public Comment postUpdate(CommentDto t, Comment d) {
		return d;
	}
}
