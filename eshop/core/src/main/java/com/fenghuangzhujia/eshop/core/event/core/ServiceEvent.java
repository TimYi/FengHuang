package com.fenghuangzhujia.eshop.core.event.core;

import java.util.EventObject;

public abstract class ServiceEvent extends EventObject {

	private static final long serialVersionUID = -1932573371278905078L;

	public ServiceEvent(Object source) {
		super(source);
	}
	
	/**
	 * 事件类型，应该是一个静态的返回值，和类型绑定在一起
	 * @return
	 */
	public abstract String getType();
}
