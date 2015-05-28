package com.fenghuangzhujia.eshop.core.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.eshop.core.event.core.EventHandlerDef;
import com.fenghuangzhujia.eshop.core.event.core.EventPublisher;
import com.fenghuangzhujia.eshop.core.event.entity.HandlerConfigRepository;

@Service
@Transactional
public class SimpleConfigService implements ConfigService {
	
	@Autowired
	private HandlerConfigRepository repository;
	@Autowired
	private EventPublisher publisher;

	@Override
	public List<EventConfig> getConfigs(String eventType) {
		List<EventConfig> configs=repository.findByEventType(eventType);
		return configs;
	}

	@Override
	public void delete(String id) {
		repository.delete(id);		
	}

	@Override
	public List<String> getConfigTypes(String eventType) {
		List<EventHandlerDef> defs=publisher.getDefs();
		if(defs==null)return null;
		List<String> types=new ArrayList<String>();
		for (EventHandlerDef def : defs) {
			String type=def.getConfigType();
			types.add(type);
		}
		return types;
	}
}
