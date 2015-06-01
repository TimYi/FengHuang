package com.fenghuangzhujia.eshop.core.event.core;

public interface EventArgsConverter<T extends EventConfig> {

	/**
	 * 是否支持对某种event转换为参数
	 * @param event
	 * @return
	 */
	boolean support(ServiceEvent event);
	
	/**
	 * 获取支持转换的事件的字符串类型
	 * @return
	 */
	String getEventType();
	
	/**
	 * 将事件转化为处理时需要的动态参数
	 * @param event
	 * @return
	 */
	EventArgs<T> convert(ServiceEvent event);
	
	/**
	 * 获取配置类型，方便和前端交互
	 * @return
	 */
	String getConfigType();
}
