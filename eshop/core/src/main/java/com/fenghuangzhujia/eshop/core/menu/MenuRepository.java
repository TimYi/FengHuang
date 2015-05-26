package com.fenghuangzhujia.eshop.core.menu;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MenuRepository extends PagingAndSortingRepository<Menu, String> {

	@Query("select c from Menu c join c.type t where t.name=?1 and c.father is null order by c.priority ASC")
	public List<Menu> findByTypeName(String type);
}
