package com.fenghuangzhujia.eshop.core.user.dto;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

public class UserDtoAdapter extends AbstractDtoAdapter<User, UserDto> {

	@Override
	public User convertToDo(UserDto t) {
		User user=BeanMapper.map(t, User.class);
		return user;
	}

	@Override
	public User update(UserDto t, User d) {
		BeanMapper.copy(t, d);
		return d;
	}

	@Override
	public UserDto convert(User source) {
		UserDto t=BeanMapper.map(source, UserDto.class);
		return t;
	}

}
