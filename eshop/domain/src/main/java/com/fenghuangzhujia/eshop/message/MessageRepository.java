package com.fenghuangzhujia.eshop.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface MessageRepository extends SpecificationRepository<Message, String> {

	Page<Message> findByUserId(String userid, Pageable pageable);
}
