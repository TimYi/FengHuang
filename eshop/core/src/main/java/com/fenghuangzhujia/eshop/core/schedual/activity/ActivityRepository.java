package com.fenghuangzhujia.eshop.core.schedual.activity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.schedual.Schedual;

public interface ActivityRepository extends PagingAndSortingRepository<Activity, String> {

	@Query("select DISTINCT a from Activity a where a.schedual=?1 and a.date>=?2 and a.date<=?3")
	List<Activity> check(Schedual schedual, LocalDate startDate, LocalDate endDate);
}
