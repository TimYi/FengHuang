package com.fenghuangzhujia.eshop.commerce.drawback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.commerce.drawback.Drawback.DrawbackStatus;

public interface DrawbackRepository extends PagingAndSortingRepository<Drawback, String> {

	Page<Drawback> findByStatus(DrawbackStatus status, Pageable pageable);
}
