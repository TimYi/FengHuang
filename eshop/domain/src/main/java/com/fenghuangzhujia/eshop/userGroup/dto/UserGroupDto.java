package com.fenghuangzhujia.eshop.userGroup.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

public class UserGroupDto extends DtoBaseModel {

	private String name;
	private long expVal;
	private int stars;
	private CategoryItemDto color;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getExpVal() {
		return expVal;
	}
	public void setExpVal(long expVal) {
		this.expVal = expVal;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public CategoryItemDto getColor() {
		return color;
	}
	public void setColor(CategoryItemDto color) {
		this.color = color;
	}
}
