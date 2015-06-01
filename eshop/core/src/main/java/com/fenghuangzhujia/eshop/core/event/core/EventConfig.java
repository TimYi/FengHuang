package com.fenghuangzhujia.eshop.core.event.core;

/**
 * 事件定义
 * @author pc
 *
 */
public interface EventConfig {
	/**
	 * 自身类型
	 * @return 应该是一个静态返回值，不需要setter提前注入
	 */
	String getType();
	
	/**
	 * 指定事件类型
	 * @return
	 */
	String getEventType();
}
