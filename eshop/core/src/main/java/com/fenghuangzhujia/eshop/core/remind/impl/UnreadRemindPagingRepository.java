package com.fenghuangzhujia.eshop.core.remind.impl;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.remind.UnreadRemindModel;
import com.fenghuangzhujia.eshop.core.remind.UnreadRemindRepository;

@NoRepositoryBean
public interface UnreadRemindPagingRepository<T extends UnreadRemindModel<ID>, ID extends Serializable>
	extends PagingAndSortingRepository<T, ID>,UnreadRemindRepository<T,ID> {
}
