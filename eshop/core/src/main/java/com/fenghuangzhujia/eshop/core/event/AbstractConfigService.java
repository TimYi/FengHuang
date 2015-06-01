package com.fenghuangzhujia.eshop.core.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.fenghuangzhujia.eshop.core.event.core.EventArgs;
import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.eshop.core.event.core.EventConfigService;

public abstract class AbstractConfigService<T extends EventConfig>
	implements EventConfigService<T> {

	@Override
	public abstract List<T> getConfigs(EventArgs<T> args);

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
