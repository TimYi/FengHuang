package com.fenghuangzhujia.eshop.user.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.user.User;

public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {
	
	public Page<Comment> findByUser(User user, Pageable pageable);
	
	public Page<Comment> findByUserId(String id, Pageable pageable);
}
