package com.fenghuangzhujia.eshop.coupons.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.coupons.Coupons;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class CouponsAdapter extends AbstractDtoAdapter<Coupons, CouponsDto, CouponsInputArgs> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public CouponsDto postConvert(Coupons d, CouponsDto t) {
		return t;
	}

	@Override
	public Coupons postConvertToDo(CouponsInputArgs i, Coupons d) {
		return postUpdate(i, d);
	}

	@Override
	public Coupons postUpdate(CouponsInputArgs i, Coupons d) {
		String userId=i.getUserId();
		if(StringUtils.isNotBlank(userId)) {
			User user=userRepository.findOne(userId);
			d.setUser(user);
		}
		return d;
	}

}
