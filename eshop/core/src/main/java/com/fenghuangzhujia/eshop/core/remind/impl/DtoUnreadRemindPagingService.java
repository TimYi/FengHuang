package com.fenghuangzhujia.eshop.core.remind.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.remind.UnreadRemindModel;
import com.fenghuangzhujia.eshop.core.remind.UnreadRemindService;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.entity.Identified;

@Transactional
public class DtoUnreadRemindPagingService<D extends UnreadRemindModel<ID>, T extends Identified<ID>,
I extends Identified<ID>, ID extends Serializable>
	extends DtoPagingService<D, T, I, ID> implements UnreadRemindService<ID> {

	@Override
	public Long countByIsReaded(String userid, boolean readed) {
		return getRepository().countByUserIdAndReaded(userid, readed);
	}

	@Override
	public void setIsReaded(ID id, boolean readed) {
		D d=getRepository().findOne(id);
		d.setReaded(readed);
		getRepository().save(d);
	}
	
	@Autowired
	public void setUnreadRemindPagingRepository(UnreadRemindPagingRepository<D, ID> repository) {
		super.setRepository(repository);
	}
	
	@Override
	public UnreadRemindPagingRepository<D, ID> getRepository() {
		return (UnreadRemindPagingRepository<D, ID>)super.getRepository();
	}
}
