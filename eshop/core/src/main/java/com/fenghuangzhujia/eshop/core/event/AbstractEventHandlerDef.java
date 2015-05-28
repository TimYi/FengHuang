package com.fenghuangzhujia.eshop.core.event;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.eshop.core.event.core.EventHandler;
import com.fenghuangzhujia.eshop.core.event.core.EventHandlerDef;
import com.fenghuangzhujia.eshop.core.event.core.ServiceEvent;

/**
 * 抽象EventHandlerDef类，通过从数据库读取自身支持的事件定义，进行相应事件处理
 * @author pc
 *
 */
public abstract class AbstractEventHandlerDef implements EventHandlerDef {
	
	protected static Logger logger=LoggerFactory.getLogger(AbstractEventHandlerDef.class);

	@Override
	public void handle(ServiceEvent event) {
		if(!support(event)) {
			logger.error("不支持的事件类型！");
			return;
		}
		List<EventConfig> configs=getConfigs(event);
		if(configs==null)return;
		for (EventConfig config : configs) {
			EventHandler handler=createHandler(config);
			handler.handle(event);
		}
	}
	
	@Override
	public boolean support(ServiceEvent event) {
		return event.getClass().equals(getSupportClass());
	}
	
	@Override
	public boolean support(String eventType) {
		return getSupportType().equals(eventType);
	}
	
	protected abstract Class<? extends ServiceEvent> getSupportClass();
	
	protected abstract String getSupportType();

	protected abstract List<EventConfig> getConfigs(ServiceEvent event);
	
	protected abstract EventHandler createHandler(EventConfig config);
}
