package com.fenghuangzhujia.eshop.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.eshop.core.remind.impl.UnreadRemindSpecificationRepository;

public interface MessageRepository extends UnreadRemindSpecificationRepository<Message, String> {

	Page<Message> findByUserId(String userid, Pageable pageable);
}
