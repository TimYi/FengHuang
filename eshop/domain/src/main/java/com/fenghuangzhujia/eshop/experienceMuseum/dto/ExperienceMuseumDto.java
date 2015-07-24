package com.fenghuangzhujia.eshop.experienceMuseum.dto;

import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class ExperienceMuseumDto extends DtoBaseModel {

	private String name;
	private AreaDto city;
	private MediaContentDto pic;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AreaDto getCity() {
		return city;
	}
	public void setCity(AreaDto city) {
		this.city = city;
	}
	public MediaContentDto getPic() {
		return pic;
	}
	public void setPic(MediaContentDto pic) {
		this.pic = pic;
	}
}
