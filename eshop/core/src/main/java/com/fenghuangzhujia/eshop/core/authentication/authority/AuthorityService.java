package com.fenghuangzhujia.eshop.core.authentication.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.role.AuthorityGroup;
import com.fenghuangzhujia.eshop.core.authentication.role.AuthorityGroupRepository;
import com.fenghuangzhujia.foundation.core.service.AbstractPagingAndSortingService;

@Service
@Transactional
public class AuthorityService extends AbstractPagingAndSortingService<Authority, String> {
	
	@Autowired
	private AuthorityGroupRepository groupRepository;
	
	@Override
	public Authority add(Authority t) {
		String groupid=t.getGroupid();
		if(groupid!=null) {
			AuthorityGroup group=groupRepository.findOne(t.getGroupid());
			t.setGroup(group);
		}		
		return super.add(t);
	}
	
	@Override
	public Authority update(Authority t) {
		String groupid=t.getGroupid();		
		Authority authority=getRepository().findOne(t.getId());
		if(groupid!=null) {
			AuthorityGroup group=groupRepository.findOne(t.getGroupid());
			authority.setGroup(group);
		}	
		authority.setAuthority(t.getAuthority());
		authority.setName(t.getName());
		authority.setDescription(t.getDescription());
		getRepository().save(authority);
		return authority;
	}
	
	public Authority getByAuthority(String authority) {
		return getRepository().getByAuthority(authority);
	}
	
	@Autowired
	protected void setAuthorityRepository(AuthorityRepository repository) {
		super.setRepository(repository);
	}
	@Override
	public AuthorityRepository getRepository() {
		return (AuthorityRepository)super.getRepository();
	}
}
