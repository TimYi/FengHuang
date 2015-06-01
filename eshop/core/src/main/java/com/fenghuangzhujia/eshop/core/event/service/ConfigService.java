package com.fenghuangzhujia.eshop.core.event.service;

import java.util.List;

import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.eshop.core.event.core.ServiceEvent;

/**
 * 为前端提供的事件定义服务
 * @author pc
 *
 */
public interface ConfigService {
	
	/**
	 * 获取某种事件类型已有事件处理定义
	 * @param event
	 * @return
	 */
	List<EventConfig> getConfigs(ServiceEvent event);
	
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
