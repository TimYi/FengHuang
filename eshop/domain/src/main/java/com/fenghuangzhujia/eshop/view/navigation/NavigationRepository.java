package com.fenghuangzhujia.eshop.view.navigation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NavigationRepository extends PagingAndSortingRepository<Navigation, String> {

	@Override
	@Query("select n from Navigation n where n.superNavigation is null")
	public Page<Navigation> findAll(Pageable pageable);
	
	@Override
	@Query("select n from Navigation n where n.superNavigation is null")
	public Iterable<Navigation> findAll();
	
	@Override
	@Query("select n from Navigation n where n.superNavigation is null")
	public Iterable<Navigation> findAll(Sort sort);
}
