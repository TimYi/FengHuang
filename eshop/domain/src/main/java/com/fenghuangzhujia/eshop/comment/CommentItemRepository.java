package com.fenghuangzhujia.eshop.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.eshop.core.remind.impl.UnreadRemindSpecificationRepository;

public interface CommentItemRepository extends UnreadRemindSpecificationRepository<CommentItem, String> {

	public Page<CommentItem> findByUserId(String id, Pageable pageable);
	
	public Page<CommentItem> findByCommentSourceid(String sourceid, Pageable pageable);
}
