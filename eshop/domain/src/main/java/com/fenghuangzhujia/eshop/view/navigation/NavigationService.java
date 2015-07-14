package com.fenghuangzhujia.eshop.view.navigation;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationDto;
import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class NavigationService extends DtoPagingService<Navigation, NavigationDto, NavigationInputArgs, String> {

	@Override
	public PagedList<NavigationDto> findPage(int page, int size) {
		Sort sort=new Sort(new Sort.Order(Direction.ASC, "ordernum"));
		PageRequest request=new PageRequest(page-1, size, sort);
		Page<Navigation> list=getRepository().findAll(request);
		Page<NavigationDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	@Override
	public Iterable<NavigationDto> findAll() {
		Sort sort=new Sort(new Sort.Order(Direction.ASC, "ordernum"));
		Iterable<Navigation> all=getRepository().findAll(sort);
		if(all==null)return null;
		return adapter.convertDoList(all);
	}
	
	public void reOrder(Map<String, Integer> orders) {
		for (Entry<String, Integer> order : orders.entrySet()) {
			String id=order.getKey();
			int ordernum=order.getValue();
			Navigation navigation=getRepository().findOne(id);
			navigation.setOrdernum(ordernum);
		}
	}
}
