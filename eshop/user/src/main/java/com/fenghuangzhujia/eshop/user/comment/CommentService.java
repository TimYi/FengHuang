package com.fenghuangzhujia.eshop.user.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.user.comment.dto.CommentDto;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class CommentService extends DtoPagingService<Comment, CommentDto, String> {
	public PagedList<CommentDto> findPage(Integer page, Integer size, User user) {
		PageRequest request=new PageRequest(page-1, size);
		Page<Comment> list=getRepository().findByUser(user, request);
		Page<CommentDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<CommentDto> findPage(Integer page, Integer size, String userid) {
		PageRequest request=new PageRequest(page-1, size);
		Page<Comment> list=getRepository().findByUserId(userid, request);
		Page<CommentDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	@Autowired
	public void setCollectRepository(CommentRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public CommentRepository getRepository() {
		return (CommentRepository)super.getRepository();
	}
}
