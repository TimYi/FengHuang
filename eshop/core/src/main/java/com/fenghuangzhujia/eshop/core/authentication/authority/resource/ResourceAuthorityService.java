package com.fenghuangzhujia.eshop.core.authentication.authority.resource;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.foundation.core.service.AbstractPagingAndSortingService;

@Service
@Transactional
public class ResourceAuthorityService extends AbstractPagingAndSortingService<ResourceAuthority, String> {

	@Override
	public ResourceAuthority findOne(String id) {
		ResourceAuthority resource=super.findOne(id);
		Hibernate.initialize(resource.getOperations());
		return resource;
	}
	
	public ResourceAuthority getByName(String name) {
		ResourceAuthority resource=getRepository().getByName(name);
		Hibernate.initialize(resource.getOperations());
		return resource;
	}
	
	@Override
	public ResourceAuthority update(ResourceAuthority t) {
		ResourceAuthority group=getRepository().findOne(t.getId());
		group.setName(t.getName());
		group.setDescription(t.getDescription());
		getRepository().save(group);
		return group;
	}
	
	@Autowired
	public void setResourceAuthorityRepository(ResourceAuthorityRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public ResourceAuthorityRepository getRepository() {
		return (ResourceAuthorityRepository)super.getRepository();
	}
}
