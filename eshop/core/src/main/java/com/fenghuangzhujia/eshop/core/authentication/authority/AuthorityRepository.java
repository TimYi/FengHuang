package com.fenghuangzhujia.eshop.core.authentication.authority;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, String> {
	Authority getByAuthority(String authority);
}
