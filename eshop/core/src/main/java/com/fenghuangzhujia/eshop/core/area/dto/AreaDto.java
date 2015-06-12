package com.fenghuangzhujia.eshop.core.area.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class AreaDto extends DtoBaseModel {
	@JsonIgnore
	private AreaDto upperArea;
	private String name;
	private AreaLevel level;
	
	public AreaDto getUpperArea() {
		return upperArea;
	}
	public void setUpperArea(AreaDto upperArea) {
		this.upperArea = upperArea;
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
