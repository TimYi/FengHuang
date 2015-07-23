package com.fenghuangzhujia.eshop.decorateProcess;

import javax.persistence.Table;

import javax.persistence.Entity;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_decorate_process")
public class DecorateProcess extends UUIDBaseModel {

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
