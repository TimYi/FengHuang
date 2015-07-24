package com.fenghuangzhujia.eshop.experienceMuseum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_experience_museum")
public class ExperienceMuseum extends UUIDBaseModel {

	private String name;
	private Area city;
	private MediaContent pic;
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(optional=false)
	public Area getCity() {
		return city;
	}
	public void setCity(Area city) {
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
