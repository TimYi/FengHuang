package com.fenghuangzhujia.eshop.core.menu;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MenuRepository extends PagingAndSortingRepository<Menu, String> {
	
	/**根据便于记忆的唯一标识查找Menu*/
	public Menu findByCode(String code);
}
