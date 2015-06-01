package com.fenghuangzhujia.eshop.core.event.core;

/**
 * 事件处理器，根据事件转化的动态参数和Config的配置参数进行处理
 * @author pc
 *
 * @param <T>
 */
public interface EventExecuter<T extends EventConfig> {

	void exe(EventArgs<T> args, T config);
}
