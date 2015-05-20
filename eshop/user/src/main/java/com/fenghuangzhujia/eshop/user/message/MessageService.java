package com.fenghuangzhujia.eshop.user.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.user.message.dto.MessageDto;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class MessageService extends DtoPagingService<Message, MessageDto, String> {
	public PagedList<MessageDto> findPage(Integer page, Integer size, User user) {
		PageRequest request=new PageRequest(page-1, size);
		Page<Message> list=getRepository().findByUser(user, request);
		Page<MessageDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<MessageDto> findPage(Integer page, Integer size, String userid) {
		PageRequest request=new PageRequest(page-1, size);
		Page<Message> list=getRepository().findByUserId(userid, request);
		Page<MessageDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	@Autowired
	public void setCollectRepository(MessageRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public MessageRepository getRepository() {
		return (MessageRepository)super.getRepository();
	}
}
