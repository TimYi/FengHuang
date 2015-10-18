package com.fenghuangzhujia.eshop.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.foundation.core.service.AbstractPagingService;

@Service
@Transactional
public class SystemUserService extends AbstractPagingService<SystemUser, String> {

	@Autowired
	private SystemUserManager manager;
	
	
	@Override
	public SystemUser add(SystemUser entity) {
		return manager.add(entity);
	}
	
	@Override
	public SystemUser update(SystemUser args) {
		return manager.update(args.getId(), args.getUsername(), args.getPassword(), args.getRealname(), args.isVerified());
	}
}
