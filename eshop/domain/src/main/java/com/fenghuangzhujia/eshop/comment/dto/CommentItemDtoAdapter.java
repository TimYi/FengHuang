package com.fenghuangzhujia.eshop.comment.dto;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.comment.Comment;
import com.fenghuangzhujia.eshop.comment.CommentItem;
import com.fenghuangzhujia.eshop.comment.CommentItemRepository;
import com.fenghuangzhujia.eshop.comment.CommentRepository;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Component
public class CommentItemDtoAdapter extends AbstractDtoAdapter<CommentItem, CommentItemDto, CommentItemInputArgs> {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentItemRepository commentItemRepository;
	
	@Override
	public CommentItemDto postConvert(CommentItem d, CommentItemDto t) {
		Set<CommentItem> replys=d.getReplys();
		if(replys!=null) {
			t.setReplyNumber(replys.size());
		}
		return t;
	}

	@Override
	public CommentItem postConvertToDo(CommentItemInputArgs t, CommentItem d) {
		return postUpdate(t, d);
	}

	@Override
	public CommentItem postUpdate(CommentItemInputArgs t, CommentItem d) {
		String sourceid=t.getSourceid();
		String userid=t.getUserid();
		if(StringUtils.isBlank(sourceid))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT,"评论id不能为空");
		if(StringUtils.isBlank(userid))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT,"用户id不能为空");
		Comment comment=commentRepository.getBySourceid(sourceid);
		//处理第一次添加评论comment的创建
		if(comment==null) {
			comment=new Comment();
			comment.setSourceid(sourceid);
			comment.setUrl(t.getUrl());
			comment.setColumn(t.getColumn());
			comment=commentRepository.save(comment);
		}
		d.setComment(comment);
		User user=userRepository.findOne(userid);
		d.setUser(user);
		String replyid=t.getReplyid();
		if(!StringUtils.isBlank(replyid)) {
			CommentItem reply=commentItemRepository.findOne(replyid);
			if(reply!=null && !reply.getComment().getSourceid().equals(sourceid))
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT,"只能在同一文章中回复评论");
			d.setReply(reply);
		}
		return d;
	}
}
