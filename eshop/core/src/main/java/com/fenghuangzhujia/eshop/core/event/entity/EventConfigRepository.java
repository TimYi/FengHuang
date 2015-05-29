package com.fenghuangzhujia.eshop.core.event.entity;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventConfigRepository extends PagingAndSortingRepository<EventConfigEntity, String> {

	/**
	 * 按照事件类型获取Handler定义
	 * @param type
	 * @return
	 */
	List<EventConfigEntity> findByEventType(String type);
}
