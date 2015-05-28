package com.fenghuangzhujia.eshop.core.event.core;

/**
 * 事件处理器定义
 * @author pc
 *
 */
public interface EventHandlerDef extends EventHandler {
	
	/**
	 * 是否支持处理某种事件
	 * @param event never @null
	 * @return
	 */
	boolean support(ServiceEvent event);
	/**
	 * 通过事件类型字符判断是否支持此种事件处理
	 * @param eventType
	 * @return
	 */
	boolean support(String eventType);
	
	/**
	 * 获取此种HandlerDef支持的ConfigType
	 * @return
	 */
	public String getConfigType();
}
