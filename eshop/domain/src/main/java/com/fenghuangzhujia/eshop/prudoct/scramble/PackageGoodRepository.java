package com.fenghuangzhujia.eshop.prudoct.scramble;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;

public interface PackageGoodRepository extends PagingAndSortingRepository<PackageGood, String> {

	/**
	 * 获取用户抢购成功的某种套餐
	 * 要求订单状态不是已完成的
	 * @param userId
	 * @param packageId
	 * @return
	 */
	@Query("select p from PackageGood p join p.decoratePackage dp,GoodOrder o join o.good g join o.user u "
			+ "where u.id=?1 and dp.id=?2 and p.id=g.id and o.status=0")
	public List<PackageGood> processingUserPackageOrder(String userId, String packageId);
	
	/**
	 * 获取用户抢购成功的某种套餐订单
	 * 要求订单状态不是已完成的
	 * @param userId
	 * @param packageId
	 * @return
	 */
	@Query("select o from PackageGood p join p.decoratePackage dp,GoodOrder o join o.good g join o.user u "
			+ "where u.id=?1 and dp.id=?2 and p.id=g.id and o.status=0")
	public List<GoodOrder> processingUserOrder(String userId, String packageId);
}
