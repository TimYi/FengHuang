package com.fenghuangzhujia.eshop.userGroup;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserGroupRepository extends PagingAndSortingRepository<UserGroup, String> {

	@Query("select g from UserGroup g where g.minExp<=?1 and g.maxExp>?1")
	public UserGroup inGroup(long expVal);
}
