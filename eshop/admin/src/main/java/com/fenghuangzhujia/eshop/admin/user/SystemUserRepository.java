package com.fenghuangzhujia.eshop.admin.user;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface SystemUserRepository extends SpecificationRepository<SystemUser, String> {

	SystemUser getByUsername(String username);
}
