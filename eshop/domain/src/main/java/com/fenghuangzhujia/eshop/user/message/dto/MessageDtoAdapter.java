package com.fenghuangzhujia.eshop.user.message.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.user.message.Message;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Component
public class MessageDtoAdapter extends AbstractDtoAdapter<Message, MessageDto> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Message convertToDo(MessageDto t) {
		if(t==null)return null;
		Message d=BeanMapper.map(t, Message.class);
		if(t.getUserid()!=null) {
			User user=userRepository.findOne(t.getUserid());
			d.setUser(user);
		}
		return d;
	}

	@Override
	public Message update(MessageDto t, Message d) {
		if(t==null||d==null)return null;
		BeanMapper.copy(t, d);
		if(t.getUserid()!=null) {
			User user=userRepository.findOne(t.getUserid());
			d.setUser(user);
		}
		return d;
	}

	@Override
	public MessageDto convert(Message source) {
		if(source==null)return null;
		MessageDto t=BeanMapper.map(source, MessageDto.class);
		t.setUserid(source.getUser().getId());
		return t;
	}
}
