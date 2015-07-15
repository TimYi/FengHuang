package com.fenghuangzhujia.eshop.experience.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ExperienceMuseumInputArgs extends DtoBaseModel {

	private String name;
	private String cityId;
	private MultipartFile picFile;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public MultipartFile getPicFile() {
		return picFile;
	}
	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
	}
}
