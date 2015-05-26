package com.fenghuangzhujia.eshop.core.column.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

public class ColumnDto extends DtoBaseModel {
	@JsonIgnore
	private String fatherid;
	private String name;
	private String description;
	private Set<ColumnDto> sons;
	@JsonIgnore
	private String typeid;
	private CategoryItemDto type;
	
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<ColumnDto> getSons() {
		return sons;
	}
	public void setSons(Set<ColumnDto> sons) {
		this.sons = sons;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public CategoryItemDto getType() {
		return type;
	}
	public void setType(CategoryItemDto type) {
		this.type = type;
	}
}
