package com.fenghuangzhujia.foundation.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.persistance.SearchFilter;

public interface SpecifactionService<T, ID extends Serializable>
	extends PagingService<T, ID> {

	PagedList<T> findAll(int page, int size,
			List<SearchFilter> filters, Map<String, String> orders);
}
