package com.fenghuangzhujia.foundation.core.service;

import java.io.Serializable;
import java.util.Map;

import com.fenghuangzhujia.foundation.core.model.PagedList;

public interface SpecifactionService<T, I, ID extends Serializable>
	extends PagingService<T, I, ID> {

	PagedList<T> findAll(int page, int size,
			Map<String, Object> filters, Map<String, Object> orders);
}
