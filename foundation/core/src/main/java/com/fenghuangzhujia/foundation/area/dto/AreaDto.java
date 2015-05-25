package com.fenghuangzhujia.foundation.area.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.foundation.area.Area.AreaLevel;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class AreaDto extends DtoBaseModel {
	@JsonIgnore
	private String upperAreaId;
	private String name;
	private AreaLevel level;
	
	public String getUpperAreaId() {
		return upperAreaId;
	}
	public void setUpperAreaId(String upperAreaId) {
		this.upperAreaId = upperAreaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AreaLevel getLevel() {
		return level;
	}
	public void setLevel(AreaLevel level) {
		this.level = level;
	}
}
