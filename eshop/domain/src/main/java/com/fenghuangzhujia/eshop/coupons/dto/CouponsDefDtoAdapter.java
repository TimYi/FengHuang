package com.fenghuangzhujia.eshop.coupons.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.coupons.entity.CouponsConstrainEntity;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsDefEntity;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsStragyEntity;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;

@Component
public class CouponsDefDtoAdapter extends AbstractDtoAdapter<CouponsDefEntity, CouponsDefDto> {

	@Autowired
	private CouponsConstrainDtoAdapter constrainAdapter;
	@Autowired
	private CouponsStragyDtoAdapter stragyAdapter;
	
	@Override
	public CouponsDefDto postConvert(CouponsDefEntity d, CouponsDefDto t) {
		CouponsConstrainDto constrain=constrainAdapter.convert(d.getConstrain());
		t.setConstrain(constrain);
		CouponsStragyDto stragy=stragyAdapter.convert(d.getStragy());
		t.setStragy(stragy);
		return t;
	}

	@Override
	public CouponsDefEntity postConvertToDo(CouponsDefDto t, CouponsDefEntity d) {
		CouponsConstrainEntity constrain=constrainAdapter.convertToDo(t.getConstrain());
		d.setConstrain(constrain);
		CouponsStragyEntity stragy=stragyAdapter.convertToDo(t.getStragy());
		d.setStragy(stragy);
		return d;
	}

	@Override
	public CouponsDefEntity postUpdate(CouponsDefDto t, CouponsDefEntity d) {
		CouponsConstrainEntity constrain=d.getConstrain();
		constrain=constrainAdapter.update(t.getConstrain(), constrain);
		d.setConstrain(constrain);
		CouponsStragyEntity stragy=d.getStragy();
		stragy=stragyAdapter.update(t.getStragy(), stragy);
		d.setStragy(stragy);
		return d;
	}

}
