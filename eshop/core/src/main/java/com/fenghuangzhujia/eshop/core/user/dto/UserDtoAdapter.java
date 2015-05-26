package com.fenghuangzhujia.eshop.core.user.dto;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;

public class UserDtoAdapter extends AbstractDtoAdapter<User, UserDto> {

	@Override
	public UserDto postConvert(User d, UserDto t) {
		return t;
	}

	@Override
	public User postConvertToDo(UserDto t, User d) {
		return d;
	}

	@Override
	public User postUpdate(UserDto t, User d) {
		return d;
	}

}
