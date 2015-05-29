package com.fenghuangzhujia.eshop.core.event;

/**
 * 事件最终引发行为的执行者
 * @author pc
 *
 */
public interface EventExecuter {
	/**
	 * 执行事件引发的某个变化
	 */
	void execute();
}
