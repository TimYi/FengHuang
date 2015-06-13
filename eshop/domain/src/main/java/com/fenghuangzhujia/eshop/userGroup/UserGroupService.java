package com.fenghuangzhujia.eshop.userGroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	
	/**
	 * 添加全部分组信息，删除已有信息
	 * @param list
	 * @return
	 */
	public List<UserGroupDto> addAll(Iterable<UserGroupInputArgs> list) {
		//保存新分组
		List<UserGroupDto> result=new ArrayList<UserGroupDto>();
		for (UserGroupInputArgs inputArgs : list) {
			String id=inputArgs.getId();
			if(StringUtils.isBlank(id)) {
				UserGroupDto group=add(inputArgs);
				result.add(group);
			} else {
				UserGroupDto group=update(inputArgs);
				if(group!=null) {
					result.add(group);
				}
			}			
		}
		return result;
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
