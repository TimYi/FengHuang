package com.fenghuangzhujia.eshop.coupons;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.fenghuangzhujia.eshop.common.remind.impl.UnreadRemindSpecificationRepository;

public interface CouponsRepository extends UnreadRemindSpecificationRepository<Coupons, String> {

	@Query("select c from Coupons c join c.user u where u.id=?1 and c.readed=false")
	List<Coupons> findUnusedCoupons(String userId);
	
	List<Coupons> findByUserId(String userId);
}
