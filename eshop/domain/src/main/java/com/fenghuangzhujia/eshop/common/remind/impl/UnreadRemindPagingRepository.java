package com.fenghuangzhujia.eshop.common.remind.impl;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.common.remind.UnreadRemindModel;
import com.fenghuangzhujia.eshop.common.remind.UnreadRemindRepository;

@NoRepositoryBean
public interface UnreadRemindPagingRepository<T extends UnreadRemindModel<ID>, ID extends Serializable>
	extends PagingAndSortingRepository<T, ID>,UnreadRemindRepository<T,ID> {
}
