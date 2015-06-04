package com.fenghuangzhujia.eshop.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.comment.dto.CommentItemDto;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class CommentItemService extends DtoSpecificationService<CommentItem, CommentItemDto, String> {

	public PagedList<CommentItemDto> findByUser(int page, int size, String userid) {
		PageRequest request=new PageRequest(page-1, size);
		Page<CommentItem> list=getRepository().findByUserId(userid, request);
		Page<CommentItemDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<CommentItemDto> findBySource(int page, int size, String sourceid) {
		PageRequest request=new PageRequest(page-1, size);
		Page<CommentItem> list=getRepository().findByCommentSourceid(sourceid, request);
		Page<CommentItemDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	@Override
	public CommentItemRepository getRepository() {
		return (CommentItemRepository)super.getRepository();
	}
	
	@Autowired
	public void setCommentItemRepository(CommentItemRepository repository) {
		super.setRepository(repository);
	}
}
