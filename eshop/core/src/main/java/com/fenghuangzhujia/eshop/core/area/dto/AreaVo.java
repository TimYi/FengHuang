package com.fenghuangzhujia.eshop.core.area.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class AreaVo extends DtoBaseModel {

	private String name;
	private AreaLevel level;
	/**区号，市一级有*/
	private String code;
	private Set<AreaVo> underAreas;
	
	public String getName() {
		return name;
	}
	public AreaLevel getLevel() {
		return level;
	}
	public String getCode() {
		return code;
	}
	public Set<AreaVo> getUnderAreas() {
		return underAreas;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLevel(AreaLevel level) {
		this.level = level;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setUnderAreas(Set<AreaVo> underAreas) {
		this.underAreas = underAreas;
	}
}
