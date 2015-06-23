package com.fenghuangzhujia.eshop.live.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.live.Live.ProjectProgress;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class LiveInputArgs extends DtoBaseModel {

	/**直播所属用户*/
	private String userId;
	/**案例名称*/
	private String name;
	/**小区名称*/
	private String village;
	/**面积*/
	private Double area;
	/**户型，如二居室*/
	private String house;
	/**开工日期*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;	
	private MultipartFile mainPicFile;
	/**是否展示*/
	private boolean shouldShow;
	private ProjectProgress status;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public boolean isShouldShow() {
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
	public MultipartFile getMainPicFile() {
		return mainPicFile;
	}
	public void setMainPicFile(MultipartFile mainPicFile) {
		this.mainPicFile = mainPicFile;
	}
}
