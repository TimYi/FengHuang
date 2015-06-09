package com.fenghuangzhujia.eshop.core.menu.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.menu.Menu;
import com.fenghuangzhujia.eshop.core.menu.MenuRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Component
public class MenuDtoAdapter extends AbstractDtoAdapter<Menu, MenuDto, MenuInputArgs> {
	
	@Autowired
	private MenuRepository columnRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;

	@Override
	public MenuDto postConvert(Menu d, MenuDto t) {
		return t;
	}

	@Override
	public Menu postConvertToDo(MenuInputArgs t, Menu d) {
		return postUpdate(t, d);
	}

	@Override
	public Menu postUpdate(MenuInputArgs t, Menu d) {
		String fatherid=t.getFatherid();
		if(StringUtils.isNotBlank(fatherid)) {
			Menu father=columnRepository.findOne(fatherid);
			d.setFather(father);
		}
		String typeid=t.getTypeid();
		if(StringUtils.isNotBlank(typeid)) {
			CategoryItem type=categoryItemRepository.findOne(typeid);
			d.setType(type);
		}
		return d;
	}

}
