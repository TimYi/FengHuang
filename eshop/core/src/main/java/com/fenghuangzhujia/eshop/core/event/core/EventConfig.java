package com.fenghuangzhujia.eshop.core.event.core;

import com.fenghuangzhujia.foundation.core.entity.Identified;

/**
 * 具体的事件处理定义
 * @author pc
 *
 */
public interface EventConfig extends Identified<String> {
	
	/**
	 * config类型
	 * @return
	 */
	String getType();
	
	/**
	 * 事件类型，每种Config可能被配置到多种事件中
	 * @return
	 */
	String getEventType();
}
