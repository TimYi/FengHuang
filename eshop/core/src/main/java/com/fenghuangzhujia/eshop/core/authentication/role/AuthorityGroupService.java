package com.fenghuangzhujia.eshop.core.authentication.role;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.foundation.core.service.AbstractPagingAndSortingService;

@Service
@Transactional
public class AuthorityGroupService extends AbstractPagingAndSortingService<AuthorityGroup, String> {
	
	@Override
	public AuthorityGroup findOne(String id) {
		AuthorityGroup group=super.findOne(id);
		Hibernate.initialize(group.getAuthorities());
		return group;
	}
	
	@Override
	public AuthorityGroup update(AuthorityGroup t) {
		AuthorityGroup group=getRepository().findOne(t.getId());
		group.setName(t.getName());
		group.setDescription(t.getDescription());
		getRepository().save(group);
		return group;
	}
}
