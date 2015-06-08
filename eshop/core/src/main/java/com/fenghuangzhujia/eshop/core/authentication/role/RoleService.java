package com.fenghuangzhujia.eshop.core.authentication.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityRepository;
import com.fenghuangzhujia.eshop.core.authentication.role.dto.RoleDto;
import com.fenghuangzhujia.eshop.core.authentication.role.dto.RoleInputArgs;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class RoleService extends DtoPagingService<Role, RoleDto, RoleInputArgs, String> {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private UserRepository userRepository;
	
	public RoleDto getByName(String name) {
		Role role=getRepository().getByName(name);
		return adapter.convert(role);
	}
	
	@Override
	public RoleRepository getRepository() {
		return (RoleRepository)super.getRepository();
	}
	public void setRoleRepository(RoleRepository repository) {
		super.setRepository(repository);
	}
}
