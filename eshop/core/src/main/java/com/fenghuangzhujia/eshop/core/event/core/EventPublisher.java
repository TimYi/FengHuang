package com.fenghuangzhujia.eshop.core.event.core;

import java.util.List;

/**
 * 事件发布总线
 * @author pc
 *
 */
public interface EventPublisher {
	void publish(ServiceEvent event);
	List<EventHandlerDef> getDefs();
}
