package com.fenghuangzhujia.eshop.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface CommentItemRepository extends SpecificationRepository<CommentItem, String> {

	public Page<CommentItem> findByUserId(String id, Pageable pageable);
	
	public Page<CommentItem> findByCommentSourceid(String sourceid, Pageable pageable);
}
