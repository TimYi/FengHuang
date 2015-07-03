package com.fenghuangzhujia.eshop.live.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.live.Live.ProjectProgress;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class LiveDto extends DtoBaseModel {

	/**直播所属用户*/
	private UserDto user;
	/**案例名称*/
	private String name;
	/**小区名称*/
	private String village;
	/**面积*/
	private Double area;
	/**户型，如二居室*/
	private String house;
	/**开工日期*/
	private Date startDate;
	/**是否展示*/
	private boolean shouldShow;
	private MediaContentDto mainPic;
	private ProjectProgress status;
	
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
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public boolean getShouldShow() {
		return shouldShow;
	}
	public void setShouldShow(boolean shouldShow) {
		this.shouldShow = shouldShow;
	}
	public ProjectProgress getStatus() {
		return status;
	}
	public void setStatus(ProjectProgress status) {
		this.status = status;
	}
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
		this.mainPic = mainPic;
	}
}
