package com.fenghuangzhujia.eshop.core.event.core;

/**
 * 事件处理器定义
 * @author pc
 *
 */
public interface EventHandlerDef {
	
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
	 * 获取此种HandlerDef支持的ConfigType，只支持单一的一种Config
	 * 可以为每种Config只定义一个HandlerDef，一个HandlerDef可以支持很多种事件。
	 * 这个可以在实现的时候通过另外的聚合转换完成
	 * @return
	 */
	public String getConfigType();
	/**
	 * 执行事件处理
	 */
	void handle(ServiceEvent event);
}
