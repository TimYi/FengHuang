package com.fenghuangzhujia.eshop.live.dto;

import java.util.Date;
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
	private Date startDate;
	/**是否展示*/
	private boolean show;
	
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
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
}
