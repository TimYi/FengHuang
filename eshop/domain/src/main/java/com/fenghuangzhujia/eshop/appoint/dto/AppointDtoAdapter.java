package com.fenghuangzhujia.eshop.appoint.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.appoint.Appoint;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.area.Area;
import com.fenghuangzhujia.foundation.area.AreaRepository;
import com.fenghuangzhujia.foundation.area.dto.AreaVo;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Component
public class AppointDtoAdapter extends AbstractDtoAdapter<Appoint, AppointDto> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	@Autowired
	private AreaRepository areaRepository;

	@Override
	public AppointDto postConvert(Appoint d, AppointDto t) {
		t.setUserid(d.getUser().getId());
		t.setArea(new AreaVo(d.getArea()));
		return t;
	}

	@Override
	public Appoint postConvertToDo(AppointDto t, Appoint d) {
		return postUpdate(t, d);
	}

	@Override
	public Appoint postUpdate(AppointDto t, Appoint d) {
		String userid=t.getUserid();
		if(StringUtils.isNotBlank(userid)) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		String typeid=t.getTypeid();
		if(StringUtils.isNotBlank(typeid)) {
			CategoryItem categoryItem=categoryItemRepository.findOne(typeid);
			d.setType(categoryItem);
		}
		String areaid=t.getAreaid();
		if(StringUtils.isNotBlank(areaid)) {
			Area area=areaRepository.findOne(areaid);
			d.setArea(area);
		}
		return d;
	}
}
