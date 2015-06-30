package com.fenghuangzhujia.eshop.core.menu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.menu.dto.MenuDto;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class MenuService extends DtoPagingService<Menu, MenuDto, MenuInputArgs, String> {
	
	public MenuDto findByCode(String code) {
		Menu menu=getRepository().findByCode(code);
		return adapter.convert(menu);
	}
	
	@Override
	public MenuRepository getRepository() {
		return (MenuRepository)super.getRepository();
	}
}
