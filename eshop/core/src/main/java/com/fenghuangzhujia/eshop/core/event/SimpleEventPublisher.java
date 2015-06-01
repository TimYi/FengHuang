package com.fenghuangzhujia.eshop.core.event;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.core.event.core.EventHandler;
import com.fenghuangzhujia.eshop.core.event.core.EventPublisher;
import com.fenghuangzhujia.eshop.core.event.core.ServiceEvent;

/**
 * 事件发布总线，需要注入EventHandler
 * @author pc
 *
 */
@Service
@Transactional
public class SimpleEventPublisher implements EventPublisher {
	
	private static Logger logger=LoggerFactory.getLogger(SimpleEventPublisher.class);
	
	@Autowired
	private List<EventHandler> handlers;
	
	/**
	 * 发布事件，按异步处理
	 */
	@Override
	public void publish(ServiceEvent event) {
		publish(event,false);
	}
	
	/**
	 * 发布事件
	 * @param event
	 * @param isSynchronized 是否按照同步处理
	 */
	public void publish(ServiceEvent event, boolean isSynchronized) {
		if(isSynchronized) {
			try {
				pub(event);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			//多线程异步事件处理，防止阻塞应用
			Thread thread=new Thread(new Runnable() {			
				@Override
				public void run() {
					try {
						pub(event);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			});
			thread.start();
		}
	}
	
	/**
	 * 实际的处理器
	 * @param event
	 */
	protected void pub(ServiceEvent event) {
		for (EventHandler handler : handlers) {
			handler.handle(event);
		}
	}
}
