package com.fenghuangzhujia.eshop.worker.dto;

import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

public class WorkerDto extends DtoBaseModel {

	private long expVal;
	private UserDto user;
	/**工人姓名*/
	private String name;
	/**工种，字典类型worker*/
	private CategoryItemDto type;
	
	public long getExpVal() {
		return expVal;
	}
	public void setExpVal(long expVal) {
		this.expVal = expVal;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryItemDto getType() {
		return type;
	}
	public void setType(CategoryItemDto type) {
		this.type = type;
	}
}
