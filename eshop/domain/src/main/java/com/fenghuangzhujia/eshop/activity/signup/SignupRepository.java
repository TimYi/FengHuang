package com.fenghuangzhujia.eshop.activity.signup;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SignupRepository extends PagingAndSortingRepository<Signup, String> {

	@Query("select s from Signup s where s.telephone=?1 or s.telephone2=?1")
	List<Signup> query(String telephone);
}
