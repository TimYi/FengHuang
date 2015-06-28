package com.fenghuangzhujia.eshop.appoint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.eshop.core.remind.impl.UnreadRemindSpecificationRepository;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

public interface AppointRepository extends UnreadRemindSpecificationRepository<Appoint, String> {
	
	Page<Appoint> findByType(CategoryItem type,Pageable pageable);
	
	Page<Appoint> findByTypeId(String typeid,Pageable pageable);
	
	Page<Appoint> findByUserId(String userid,Pageable pageable);
	
	/**
	 * 获取用户已读或者未读消息数量
	 * @param userid
	 * @param readed
	 * @return
	 */
	Long countByUserIdAndReaded(String userid, boolean readed);
}
