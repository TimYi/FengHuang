package com.fenghuangzhujia.eshop.core.event.entity;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.eshop.core.event.core.EventConfig;

public interface HandlerConfigRepository extends PagingAndSortingRepository<HandlerConfigEntity, String> {

	/**
	 * 按照事件类型获取Handler定义
	 * @param type
	 * @return
	 */
	List<EventConfig> findByEventType(String type);
}
