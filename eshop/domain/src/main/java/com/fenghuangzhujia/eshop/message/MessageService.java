package com.fenghuangzhujia.eshop.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.message.dto.MessageDto;
import com.fenghuangzhujia.eshop.message.dto.MessageInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class MessageService extends DtoSpecificationService<Message, MessageDto, MessageInputArgs, String> {

	public PagedList<MessageDto> findByUser(String userid, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<Message> list=getRepository().findByUserId(userid, pageable);
		Page<MessageDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	@Autowired
	public void setMessageRepository(MessageRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public MessageRepository getRepository() {
		return (MessageRepository)super.getRepository();
	}
}
