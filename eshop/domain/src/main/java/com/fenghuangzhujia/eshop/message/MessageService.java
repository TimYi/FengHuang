package com.fenghuangzhujia.eshop.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.remind.impl.DtoUnreadRemindSpecificationService;
import com.fenghuangzhujia.eshop.message.dto.MessageDto;
import com.fenghuangzhujia.eshop.message.dto.MessageInputArgs;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class MessageService extends DtoUnreadRemindSpecificationService<Message, MessageDto, MessageInputArgs, String> {

	public PagedList<MessageDto> findByUser(String userid, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<Message> list=getRepository().findByUserId(userid, pageable);
		Page<MessageDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public MessageDto findOneByUser(String userid, String id) {
		Message message=getRepository().findOne(id);
		if(!message.getUser().getId().equals(userid))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "您只能获取自己的留言");
		return adapter.convertToDetailedDto(message);
	}
	
	public void deleteByUser(String userid, String id) {
		Message message=getRepository().findOne(id);
		if(message==null)return;
		if(!message.getUser().getId().equals(userid))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "您只能删除自己的留言");
	    getRepository().delete(message);
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
