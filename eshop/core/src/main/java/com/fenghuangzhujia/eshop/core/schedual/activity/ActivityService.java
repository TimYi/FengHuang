package com.fenghuangzhujia.eshop.core.schedual.activity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.foundation.core.service.AbstractPagingAndSortingService;

@Transactional
@Service
public class ActivityService extends AbstractPagingAndSortingService<Activity, String> {

	@Override
	public Activity update(Activity t) {
		String id=t.getId();
		Activity o=getRepository().findOne(id);
		o.setOccupied(t.isOccupied());
		getRepository().save(o);
		return t;
	}
	
	/**
	 * 修改活动的忙、闲状态
	 * @param id
	 * @param isOccupied Active是否被占用
	 * @return
	 */
	public Activity setOccupied(String id, boolean isOccupied) {
		Activity o=getRepository().findOne(id);
		o.setOccupied(isOccupied);
		getRepository().save(o);
		return o;
	}
}
