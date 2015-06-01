package com.fenghuangzhujia.eshop.core.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.fenghuangzhujia.eshop.core.event.core.EventArgs;
import com.fenghuangzhujia.eshop.core.event.core.EventArgsConverter;
import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.eshop.core.event.core.ServiceEvent;

public abstract class AbstractEventArgsConverter<T extends EventConfig> 
	implements EventArgsConverter<T> {

	@Override
	public boolean support(ServiceEvent event) {
		if(event.getClass().equals(getEventClass()))return true;
		return false;
	}
	
	/**
	 * 通过获取泛型真正参数，调用getType方法返回ConfigType
	 * @return
	 */
	@Override
	public String getConfigType() {
		try {
			T config=getTClass().newInstance();
			return config.getType();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public abstract EventArgs<T> convert(ServiceEvent event);
	
	/**
	 * 通过反射穿件Event实例，并返回其type
	 * 要保证ServiceEvent有无参构造函数，并返回一个和类型绑定的type值
	 * @return
	 */
	@Override
	public String getEventType() {
		try {
			ServiceEvent event=getEventClass().newInstance();
			return event.getType();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected abstract Class<? extends ServiceEvent> getEventClass();
	
	@SuppressWarnings("unchecked")
	protected Class<T> getTClass() {
		Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        Type[] p = ((ParameterizedType)t).getActualTypeArguments();
        entityClass = (Class<T>)p[0];
        return entityClass;
	}
}
