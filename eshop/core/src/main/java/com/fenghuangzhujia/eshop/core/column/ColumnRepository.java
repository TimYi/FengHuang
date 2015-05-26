package com.fenghuangzhujia.eshop.core.column;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColumnRepository extends PagingAndSortingRepository<Column, String> {

	@Query("select c from Column c join c.type t where t.name=?1 and c.father is null order by c.priority ASC")
	public List<Column> findByTypeName(String type);
}
