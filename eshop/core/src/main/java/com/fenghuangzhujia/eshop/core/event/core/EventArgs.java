package com.fenghuangzhujia.eshop.core.event.core;

public interface EventArgs<T extends EventConfig> {

	String getEventType();
	
	Class<T> getConfigClass();
}
