package com.fenghuangzhujia.eshop.coupons;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.coupons.entity.CouponsEntity;

public interface CouponsRepository extends PagingAndSortingRepository<CouponsEntity, String> {

	/**
	 * 获取用户的所有优惠券
	 * @param userid
	 * @return
	 */
	List<CouponsEntity> findByUserId(String userid);
	
	/**
	 * 获取用户未使用且未过期优惠券
	 * @param userid
	 * @return
	 */
	@Query("select c from CouponsEntity c join c.user u"
			+ " where u.id=?1 and c.expired=false and c.used=false")
	List<CouponsEntity> findCouponsCouldUse(String userid);
}
