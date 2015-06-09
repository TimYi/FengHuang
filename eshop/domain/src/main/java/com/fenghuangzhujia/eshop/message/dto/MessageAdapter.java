package com.fenghuangzhujia.eshop.message.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.message.Message;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Component
public class MessageAdapter extends AbstractDtoAdapter<Message, MessageDto, MessageInputArgs> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageDto postConvert(Message d, MessageDto t) {
		return t;
	}

	@Override
	public Message postConvertToDo(MessageInputArgs i, Message d) {
		return postUpdate(i, d);
	}

	@Override
	public Message postUpdate(MessageInputArgs i, Message d) {
		String userid=i.getUserid();
		if(StringUtils.isBlank(userid))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户id不能为空");
		User user=userRepository.findOne(userid);
		d.setUser(user);
		return d;
	}

}
