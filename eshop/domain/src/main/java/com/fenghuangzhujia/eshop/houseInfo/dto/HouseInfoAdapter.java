package com.fenghuangzhujia.eshop.houseInfo.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.houseInfo.HouseInfo;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Component
public class HouseInfoAdapter extends AbstractDtoAdapter<HouseInfo, HouseInfoDto, HouseInfoInputArgs> {

	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public HouseInfoDto postConvert(HouseInfo d, HouseInfoDto t) {
		return t;
	}

	@Override
	public HouseInfo postConvertToDo(HouseInfoInputArgs i, HouseInfo d) {
		d=postUpdate(i, d);
		String userId=i.getUserId();
		if(StringUtils.isBlank(userId))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户id不能为空");
		User user=userRepository.findOne(userId);
		d.setUser(user);
		return d;
	}

	@Override
	public HouseInfo postUpdate(HouseInfoInputArgs i, HouseInfo d) {
		String cityId=i.getCityId();
		if(StringUtils.isNotBlank(cityId)) {
			Area city=areaRepository.findOne(cityId);
			if(!city.getLevel().equals(AreaLevel.CITY))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "请确认选择的是一个城市");
			d.setCity(city);
		}
		String houseTypeId=i.getHouseTypeId();
		if(StringUtils.isNotBlank(houseTypeId)) {
			CategoryItem houseType=categoryItemRepository.findOne(houseTypeId);
			d.setHouseType(houseType);
		}
		String decorateTypeId=i.getDecorateTypeId();
		if(StringUtils.isNotBlank(decorateTypeId)) {
			CategoryItem decorateType=categoryItemRepository.findOne(decorateTypeId);
			d.setDecorateType(decorateType);
		}
		return d;
	}
}
