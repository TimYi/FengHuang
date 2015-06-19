package com.fenghuangzhujia.eshop.userGroup.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.userGroup.UserGroup;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Component
public class UserGroupAdapter extends AbstractDtoAdapter<UserGroup, UserGroupDto, UserGroupInputArgs> {

	@Autowired
	private CategoryItemRepository categoryItemRepository;
	
	@Override
	public UserGroupDto postConvert(UserGroup d, UserGroupDto t) {
		return t;
	}

	@Override
	public UserGroup postConvertToDo(UserGroupInputArgs i, UserGroup d) {
		return postUpdate(i, d);
	}

	@Override
	public UserGroup postUpdate(UserGroupInputArgs i, UserGroup d) {
		String colorId=i.getColorId();
		if(StringUtils.isNotBlank(colorId)) {
			CategoryItem color=categoryItemRepository.findOne(colorId);
			d.setColor(color);
		}
		return d;
	}
}
