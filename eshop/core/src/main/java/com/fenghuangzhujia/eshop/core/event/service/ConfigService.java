package com.fenghuangzhujia.eshop.core.event.service;

import java.util.List;

import com.fenghuangzhujia.eshop.core.event.core.EventConfig;

/**
 * 从数据库（或者其它源）获取事件定义
 * @author pc
 *
 */
public interface ConfigService {
	
	/**
	 * 获取某种事件类型已有事件处理定义
	 * @param event
	 * @return
	 */
	List<EventConfig> getConfigs(String eventType);
	
	/**
	 * 删除事件定义
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 获取某种事件的全部可用ConfigType类型
	 * @param eventType
	 * @return
	 */
	List<String> getConfigTypes(String eventType);
}
