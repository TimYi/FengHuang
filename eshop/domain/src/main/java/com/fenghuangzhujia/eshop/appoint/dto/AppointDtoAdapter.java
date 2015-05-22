package com.fenghuangzhujia.eshop.appoint.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.appoint.Appoint;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Component
public class AppointDtoAdapter extends AbstractDtoAdapter<Appoint, AppointDto> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;

	@Override
	public Appoint convertToDo(AppointDto t) {
		Appoint d=BeanMapper.map(t, Appoint.class);
		String userid=t.getUserid();
		if(userid!=null) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		String typeid=t.getTypeid();
		if(typeid!=null) {
			CategoryItem categoryItem=categoryItemRepository.findOne(typeid);
			d.setType(categoryItem);
		}
		return d;
	}

	@Override
	public Appoint update(AppointDto t, Appoint d) {
		BeanMapper.copy(d, t);
		String userid=t.getUserid();
		if(userid!=null) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		String typeid=t.getTypeid();
		if(typeid!=null) {
			CategoryItem categoryItem=categoryItemRepository.findOne(typeid);
			d.setType(categoryItem);
		}
		return d;
	}

	@Override
	public AppointDto convert(Appoint source) {
		AppointDto t=BeanMapper.map(source, AppointDto.class);
		t.setUserid(source.getUser().getId());
		return t;
	}
}
