package com.fenghuangzhujia.eshop.userGroup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupDto;
import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class UserGroupService extends DtoPagingService<UserGroup, UserGroupDto, UserGroupInputArgs, String> {

}
