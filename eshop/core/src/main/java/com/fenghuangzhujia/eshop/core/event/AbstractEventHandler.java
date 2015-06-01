package com.fenghuangzhujia.eshop.core.event;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fenghuangzhujia.eshop.core.event.core.EventArgs;
import com.fenghuangzhujia.eshop.core.event.core.EventArgsConverter;
import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.eshop.core.event.core.EventConfigService;
import com.fenghuangzhujia.eshop.core.event.core.EventExecuter;
import com.fenghuangzhujia.eshop.core.event.core.EventHandler;
import com.fenghuangzhujia.eshop.core.event.core.ServiceEvent;

/**
 * 抽象EventHandlerDef类，通过从数据库读取自身支持的事件定义，进行相应事件处理
 * 应该注入事件参数转换器，以及相应ConfigService，需要定义处理函数
 * @author pc
 *
 */
public abstract class AbstractEventHandler<T extends EventConfig> implements EventHandler,EventExecuter<T> {
	
	protected static Logger logger=LoggerFactory.getLogger(AbstractEventHandler.class);

	@Autowired
	protected List<EventArgsConverter<T>> converters;
	
	@Autowired
	protected EventConfigService<T> service;

	@Override
	public void handle(ServiceEvent event) {
		EventArgs<T> args=getArgs(event);
		if(args==null)return;//如果是不支持的事件类型，返回空值
		List<T> configs=getConfigs(args);
		if(configs==null)return;
		for (T config : configs) {
			exe(args, config);
		}		
	}

	@Override
	public abstract void exe(EventArgs<T> args, T config);
	
	protected EventArgs<T> getArgs(ServiceEvent event) {
		for (EventArgsConverter<T> converter : converters) {
			if(converter.support(event)) {
				EventArgs<T> args=converter.convert(event);
				return args;
			}
		}
		return null;
	}
	
	protected List<T> getConfigs(EventArgs<T> args) {
		return service.getConfigs(args);
	}
}
