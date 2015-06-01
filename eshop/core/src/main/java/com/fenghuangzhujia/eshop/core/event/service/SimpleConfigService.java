package com.fenghuangzhujia.eshop.core.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.core.event.core.EventArgs;
import com.fenghuangzhujia.eshop.core.event.core.EventArgsConverter;
import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.eshop.core.event.core.EventConfigService;
import com.fenghuangzhujia.eshop.core.event.core.ServiceEvent;
import com.fenghuangzhujia.eshop.core.event.entity.EventConfigRepository;

@Service
@Transactional
public class SimpleConfigService implements ConfigService {
	
	@Autowired
	protected EventConfigRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected List<EventArgsConverter> converters;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected List<EventConfigService> services;

	/**
	 * 通过匹配Converter和ConfigService，获取全部ConfigService
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EventConfig> getConfigs(ServiceEvent event) {
		List<EventConfig> configs=new ArrayList<>();
		List<EventArgs> args=new ArrayList<>();
		for (EventArgsConverter converter : converters) {
			if(converter.support(event)) {
				EventArgs arg=converter.convert(event);
				args.add(arg);
			}
		}
		for (EventArgs arg : args) {
			for (EventConfigService service : services) {
				if(arg.getConfigClass().equals(service.getConfigClass())) {
					List<? extends EventConfig> configList=service.getConfigs(arg);
					if(configList==null)continue;
					configs.addAll(configList);
				}
			}
		}
		return configs;
	}

	@Override
	public void delete(String id) {
		repository.delete(id);
	}

	/**
	 * 获取所有converter支持的Config类型
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<String> getConfigTypes(String eventType) {
		List<String> configs=new ArrayList<String>();
		for (EventArgsConverter converter : converters) {
			if(converter.getEventType().equals(eventType)) {
				String config=converter.getConfigType();
				configs.add(config);
			}
		}
		return configs;
	}
}
