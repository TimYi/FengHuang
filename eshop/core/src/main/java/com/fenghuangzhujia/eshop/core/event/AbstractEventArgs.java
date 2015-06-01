package com.fenghuangzhujia.eshop.core.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.fenghuangzhujia.eshop.core.event.core.EventArgs;
import com.fenghuangzhujia.eshop.core.event.core.EventConfig;

public abstract class AbstractEventArgs<T extends EventConfig> implements EventArgs<T> {

	@Override
	public abstract String getEventType();

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getConfigClass() {
		Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        Type[] p = ((ParameterizedType)t).getActualTypeArguments();
        entityClass = (Class<T>)p[0];
        return entityClass;
	}
}
