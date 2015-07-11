package com.fenghuangzhujia.eshop.core.area;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.area.dto.AreaInputArgs;
import com.fenghuangzhujia.eshop.core.area.dto.AreaVo;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Service
@Transactional
public class AreaService extends DtoSpecificationService<Area, AreaDto, AreaInputArgs, String> {
	
	/**
	 * 按照区域等级获取全部区域
	 * @param level
	 * @return
	 */
	public List<AreaDto> findByLevel(AreaLevel level) {
		List<Area> areas=getRepository().findByLevel(level);
		List<AreaDto> result=new ArrayList<AreaDto>();
		result.addAll(adapter.convertDoList(areas));
		return result;
	}
	
	/**
	 * 按照上级区域获取全部区域
	 * @param id 上级区域id
	 * @return
	 */
	public List<AreaDto> findByUpperArea(String id) {
		List<Area> areas=getRepository().findByUpperAreaId(id);
		List<AreaDto> result=new ArrayList<AreaDto>();
		result.addAll(adapter.convertDoList(areas));
		return result;
	}
	
	public List<AreaVo> findVoByLevel(AreaLevel level) {
		List<Area> areas=getRepository().findByLevel(level);
		List<AreaVo> result=BeanMapper.mapList(areas, AreaVo.class);
		return result;
	}
	
	public void setAreaRepository(AreaRepository repository) {
		super.setPagingAndSortingRepository(repository);
	}
	
	@Override
	public AreaRepository getRepository() {
		return (AreaRepository)super.getRepository();
	}
}
