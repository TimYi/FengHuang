package com.fenghuangzhujia.eshop.core.menu.dto;

import java.util.Set;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

public class MenuDto extends DtoBaseModel {

	private String name;
	private String description;
	private Set<MenuVo> sons;
	private CategoryItemDto type;
	/**方便记忆的标识码*/
	private String code;
	
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
	public Set<MenuVo> getSons() {
		return sons;
	}
	public void setSons(Set<MenuVo> sons) {
		this.sons = sons;
	}
	public CategoryItemDto getType() {
		return type;
	}
	public void setType(CategoryItemDto type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
