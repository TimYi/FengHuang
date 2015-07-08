package com.fenghuangzhujia.eshop.core.area.dto;

import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class AreaInputArgs extends DtoBaseModel {

	private String upperAreaId;
	private String name;
	private AreaLevel level;
	/**区号，市一级有*/
	private String code;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
