package com.fenghuangzhujia.eshop.decorateProcess.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class DecorateProcessInputArgs extends DtoBaseModel {

	/**第几天*/
	private int day;
	/**工作内容*/
	private String content;
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
