package com.fenghuangzhujia.eshop.user.message.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.user.message.Message;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;

@Component
public class MessageDtoAdapter extends AbstractDtoAdapter<Message, MessageDto> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageDto postConvert(Message d, MessageDto t) {
		t.setUserid(d.getUser().getId());
		return t;
	}

	@Override
	public Message postConvertToDo(MessageDto t, Message d) {
		return postUpdate(t, d);
	}

	@Override
	public Message postUpdate(MessageDto t, Message d) {
		String userid=t.getUserid();
		if(StringUtils.isNotBlank(userid)) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		return d;
	}
}
