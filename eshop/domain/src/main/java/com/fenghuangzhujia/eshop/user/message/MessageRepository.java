package com.fenghuangzhujia.eshop.user.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.user.User;

public interface MessageRepository extends PagingAndSortingRepository<Message, String> {
	
	public Page<Message> findByUser(User user, Pageable pageable);
	
	public Page<Message> findByUserId(String id, Pageable pageable);
}
