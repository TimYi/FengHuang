package com.fenghuangzhujia.eshop.core.event.core;

/**
 * 事件发布总线
 * @author pc
 *
 */
public interface EventPublisher {
	void publish(ServiceEvent event);
}
