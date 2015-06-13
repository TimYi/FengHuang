package com.fenghuangzhujia.eshop.common.remind.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.common.remind.UnreadRemindModel;
import com.fenghuangzhujia.eshop.common.remind.UnreadRemindService;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.entity.Identified;

@Transactional
public class DtoUnreadRemindSpecificationService <D extends UnreadRemindModel<ID>, T extends Identified<ID>,
I extends Identified<ID>, ID extends Serializable>
extends DtoSpecificationService<D, T, I, ID> implements UnreadRemindService<ID> {

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
	public void setUnreadRemindSpecificationRepository(UnreadRemindSpecificationRepository<D, ID> repository) {
		super.setRepository(repository);
	}
	
	@Override
	public UnreadRemindSpecificationRepository<D, ID> getRepository() {
		return (UnreadRemindSpecificationRepository<D, ID>)super.getRepository();
	}

}
