package com.fenghuangzhujia.eshop.experience;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_experience_museum")
public class ExperienceMuseum extends UUIDBaseModel {

	private String name;
	private Area city;
	private MediaContent pic;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	public Area getCity() {
		return city;
	}
	public void setCity(Area city) {
		if(city !=null && !city.getLevel().equals(AreaLevel.CITY)) 
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "只能指定城市");
		this.city = city;
	}
	@OneToOne
	public MediaContent getPic() {
		return pic;
	}
	public void setPic(MediaContent pic) {
		this.pic = pic;
	}
}
