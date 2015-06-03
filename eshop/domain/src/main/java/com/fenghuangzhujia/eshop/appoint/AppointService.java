package com.fenghuangzhujia.eshop.appoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class AppointService extends DtoSpecificationService<Appoint, AppointDto, String> {
	public PagedList<AppointDto> findByTypeId(String typeid, int page, int size) {
		PageRequest pageable=new PageRequest(page, size);
		Page<Appoint> list=getRepository().findByTypeId(typeid, pageable);
		Page<AppointDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<AppointDto> findByUserId(String userid, int page, int size) {
		PageRequest pageable=new PageRequest(page, size);
		Page<Appoint> list=getRepository().findByUserId(userid, pageable);
		Page<AppointDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public AppointDto getUserAppoint(String userid, String id) {
		Appoint appoint=getRepository().findOne(id);
		if(!appoint.getUser().getId().equals(userid))return null;
		return adapter.convert(appoint);
	}
	
	@Autowired
	public void setAppointRepository(AppointRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public AppointRepository getRepository() {
		return (AppointRepository)super.getRepository();
	}
}
