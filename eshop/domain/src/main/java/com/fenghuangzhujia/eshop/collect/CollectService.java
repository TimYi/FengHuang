package com.fenghuangzhujia.eshop.collect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.collect.dto.CollectDto;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class CollectService extends DtoPagingService<Collect, CollectDto, String> {
	
	public PagedList<CollectDto> findPage(int page, int size, String userid) {
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
