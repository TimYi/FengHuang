package com.fenghuangzhujia.eshop.core.event.core;

/**
 * 事件处理器定义
 * @author pc
 *
 */
public interface EventHandler {
	
	/**
	 * 执行事件处理，如果是不支持的事件类型，则不处理
	 */
	void handle(ServiceEvent event);
}
