package com.fenghuangzhujia.eshop.prudoct.scramble;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PackageGoodRepository extends PagingAndSortingRepository<PackageGood, String> {

	/**
	 * 获取用户抢购成功的某种套餐
	 * 要求订单状态不是已完成的
	 * @param userId
	 * @param packageId
	 * @return
	 */
	@Query("select p from PackageGood p join p.decoratePackage dp,GoodOrder o join o.good g join o.user u "
			+ "where u.id=?1 and dp.id=?2 and p.id=g.id")
	public List<PackageGood> processingUserPackageOrder(String userId, String packageId);
}
