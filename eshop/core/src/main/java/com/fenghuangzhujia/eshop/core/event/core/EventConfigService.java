package com.fenghuangzhujia.eshop.core.event.core;

import java.util.List;

public interface EventConfigService<T extends EventConfig> {

	/**
	 * 根据事件参数获取相应config
	 * @param args
	 * @return
	 */
	List<T> getConfigs(EventArgs<T> args);
	
	Class<T> getConfigClass();
}
