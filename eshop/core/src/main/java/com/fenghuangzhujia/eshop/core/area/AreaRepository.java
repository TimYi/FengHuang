package com.fenghuangzhujia.eshop.core.area;

import java.util.List;

import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface AreaRepository extends SpecificationRepository<Area, String> {
	
	/**
	 * 按照区域等级获取全部区域
	 * @param level
	 * @return
	 */
	List<Area> findByLevel(AreaLevel level);
	
	/**
	 * 按照上级区域id获取全部区域
	 * @param id
	 * @return
	 */
	List<Area> findByUpperAreaId(String id);
}
