package com.fenghuangzhujia.eshop.user.collect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.user.collect.dto.CollectDto;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class CollectService extends DtoPagingService<Collect, CollectDto, String> {
	
	public PagedList<CollectDto> findPage(Integer page, Integer size, User user) {
		PageRequest request=new PageRequest(page-1, size);
		Page<Collect> list=getRepository().findByUser(user, request);
		Page<CollectDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<CollectDto> findPage(Integer page, Integer size, String userid) {
		PageRequest request=new PageRequest(page-1, size);
		Page<Collect> list=getRepository().findByUserId(userid, request);
		Page<CollectDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	@Autowired
	public void setCollectRepository(CollectRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public CollectRepository getRepository() {
		return (CollectRepository)super.getRepository();
	}
}
