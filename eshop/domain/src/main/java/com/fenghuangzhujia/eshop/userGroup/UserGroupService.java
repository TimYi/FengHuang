package com.fenghuangzhujia.eshop.userGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupDto;
import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class UserGroupService extends DtoPagingService<UserGroup, UserGroupDto, UserGroupInputArgs, String> {

	public UserGroupDto inGroup(long expVal) {
		UserGroup group=getRepository().inGroup(expVal);
		return adapter.convert(group);
	}
	
	@Autowired
	public void setUserGroupRepository(UserGroupRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public UserGroupRepository getRepository() {
		return (UserGroupRepository)super.getRepository();
	}
}
