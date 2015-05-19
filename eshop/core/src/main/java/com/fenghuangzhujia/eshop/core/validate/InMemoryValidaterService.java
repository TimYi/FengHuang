package com.fenghuangzhujia.eshop.core.validate;

import java.util.HashMap;
import java.util.Map;

import com.fenghuangzhujia.eshop.core.validate.core.Validater;
import com.fenghuangzhujia.eshop.core.validate.core.ValidaterService;

public class InMemoryValidaterService implements ValidaterService {
	protected static Map<String, Validater> validaterMap=new HashMap<String, Validater>();
	private static Object lock = new Object();

	@Override
	public void add(Validater validater) {
		synchronized (lock) {
			validaterMap.put(validater.getId(), validater);
		}		
	}

	@Override
	public Validater get(String id) {
		synchronized (lock) {
			return validaterMap.get(id);
		}
	}

	@Override
	public void delete(String id) {
		synchronized (lock) {
			validaterMap.remove(id);
		}
	}
}
