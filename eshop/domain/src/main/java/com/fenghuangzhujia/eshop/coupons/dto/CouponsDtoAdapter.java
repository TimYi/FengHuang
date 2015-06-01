package com.fenghuangzhujia.eshop.coupons.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsConstrainEntity;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsEntity;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsStragyEntity;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;

@Component
public class CouponsDtoAdapter 
	extends AbstractDtoAdapter<CouponsEntity, CouponsDto> {

	@Autowired
	private CouponsConstrainDtoAdapter constrainAdapter;
	@Autowired
	private CouponsStragyDtoAdapter stragyAdapter;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public CouponsDto postConvert(CouponsEntity d, CouponsDto t) {
		CouponsConstrainDto constrain=constrainAdapter.convert(d.getConstrain());
		t.setConstrain(constrain);
		CouponsStragyDto stragy=stragyAdapter.convert(d.getStragy());
		t.setStragy(stragy);		
		return t;
	}

	@Override
	public CouponsEntity postConvertToDo(CouponsDto t, CouponsEntity d) {
		CouponsConstrainEntity constrain=constrainAdapter.convertToDo(t.getConstrain());
		d.setConstrain(constrain);
		CouponsStragyEntity stragy=stragyAdapter.convertToDo(t.getStragy());
		d.setStragy(stragy);
		String userid=t.getUserid();
		User user=userRepository.findOne(userid);
		d.setUser(user);
		return d;
	}

	@Override
	public CouponsEntity postUpdate(CouponsDto t, CouponsEntity d) {
		CouponsConstrainEntity constrain=d.getConstrain();
		constrain=constrainAdapter.update(t.getConstrain(), constrain);
		d.setConstrain(constrain);
		CouponsStragyEntity stragy=d.getStragy();
		stragy=stragyAdapter.update(t.getStragy(), stragy);
		d.setStragy(stragy);
		String userid=t.getUserid();
		User user=userRepository.findOne(userid);
		d.setUser(user);
		return d;
	}

}
