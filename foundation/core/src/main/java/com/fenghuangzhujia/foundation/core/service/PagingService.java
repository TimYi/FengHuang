package com.fenghuangzhujia.foundation.core.service;

import java.io.Serializable;

import com.fenghuangzhujia.foundation.core.model.PagedList;

public interface PagingService<T, I, ID extends Serializable> extends CrudService<T, I, ID> {
	
	/**
	 * Returns a {@link PagedList} of entities meeting the paging restriction.
	 * @param page page number from 1
	 * @param size page size
	 * @return
	 */
	PagedList<T> findPage(int page, int size);
}
