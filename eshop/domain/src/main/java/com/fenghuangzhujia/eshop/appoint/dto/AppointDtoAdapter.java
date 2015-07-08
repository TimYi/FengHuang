package com.fenghuangzhujia.eshop.appoint.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.appoint.Appoint;
import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Component
public class AppointDtoAdapter extends AbstractDtoAdapter<Appoint, AppointDto, AppointInputArgs> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	@Autowired
	private AreaRepository areaRepository;

	@Override
	public AppointDto postConvert(Appoint d, AppointDto t) {
		return t;
	}

	@Override
	public Appoint postConvertToDo(AppointInputArgs t, Appoint d) {
		d=postUpdate(t, d);
		//为预约按照编码规则分配可记忆的唯一编码
		String areaCode=d.getCity().getCode();
		String code=CodeGenerater.generateCode(Appoint.TYPE_CODE, areaCode);
		d.setCode(code);
		return d;
	}

	@Override
	public Appoint postUpdate(AppointInputArgs t, Appoint d) {
		String userid=t.getUserId();
		if(StringUtils.isNotBlank(userid)) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		String typeid=t.getTypeId();
		if(StringUtils.isNotBlank(typeid)) {
			CategoryItem categoryItem=categoryItemRepository.findOne(typeid);
			d.setType(categoryItem);
		}
		String cityId=t.getCityId();
		if(StringUtils.isNotBlank(cityId)) {
			Area city=areaRepository.findOne(cityId);
			if(city==null || !city.getLevel().equals(AreaLevel.CITY)) 
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "城市等级错误");
			d.setCity(city);
		}
		return d;
	}
}
