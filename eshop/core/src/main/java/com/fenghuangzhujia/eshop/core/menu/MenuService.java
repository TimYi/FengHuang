package com.fenghuangzhujia.eshop.core.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.menu.dto.MenuDto;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class MenuService extends DtoPagingService<Menu, MenuDto, MenuDto, String> {
	public List<MenuDto> findByTypeName(String type) {
		List<Menu> columns=getRepository().findByTypeName(type);
		if(columns==null)return null;
		List<MenuDto> result=new ArrayList<>();
		result.addAll(adapter.convertDoList(columns));
		return result;
	}
	
	@Override
	public MenuRepository getRepository() {
		return (MenuRepository)super.getRepository();
	}
}
