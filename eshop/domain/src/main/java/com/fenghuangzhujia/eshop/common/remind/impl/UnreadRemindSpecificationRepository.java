package com.fenghuangzhujia.eshop.common.remind.impl;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

import com.fenghuangzhujia.eshop.common.remind.UnreadRemindModel;
import com.fenghuangzhujia.eshop.common.remind.UnreadRemindRepository;
import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

@NoRepositoryBean
public interface UnreadRemindSpecificationRepository<T extends UnreadRemindModel<ID>, ID extends Serializable>
	extends SpecificationRepository<T, ID>,UnreadRemindRepository<T,ID> {
}
