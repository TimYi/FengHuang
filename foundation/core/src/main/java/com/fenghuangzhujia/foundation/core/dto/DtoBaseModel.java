package com.fenghuangzhujia.foundation.core.dto;

import com.fenghuangzhujia.foundation.core.entity.Identified;

public class DtoBaseModel implements Identified<String> {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
