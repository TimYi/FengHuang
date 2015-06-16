package com.fenghuangzhujia.eshop.live.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.live.Live;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class LiveAdapter extends AbstractDtoAdapter<Live, LiveDto, LiveInputArgs> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public LiveDto postConvert(Live d, LiveDto t) {
		return t;
	}

	@Override
	public Live postConvertToDo(LiveInputArgs i, Live d) {
		return postUpdate(i, d);
	}

	@Override
	public Live postUpdate(LiveInputArgs i, Live d) {
		String userId=i.getUserId();
		User user=userRepository.findOne(userId);
		d.setUser(user);
		return d;
	}

}
