package com.fenghuangzhujia.eshop.core.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface UserRepository extends SpecificationRepository<User, String> {
	
	User getByUsername(String username);
	User getByMobile(String mobile);
	User getByQqid(String qqid);
	
	@Override
	@Modifying
	@Query("update User u set u.deleted=true where u.id=?1")
	public void delete(String id);
}
