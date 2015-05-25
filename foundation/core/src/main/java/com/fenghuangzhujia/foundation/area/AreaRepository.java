package com.fenghuangzhujia.foundation.area;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fenghuangzhujia.foundation.area.Area.AreaLevel;

public interface AreaRepository extends PagingAndSortingRepository<Area, String> {
	
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
