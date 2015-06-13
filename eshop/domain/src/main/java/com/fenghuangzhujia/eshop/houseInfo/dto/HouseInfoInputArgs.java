package com.fenghuangzhujia.eshop.houseInfo.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class HouseInfoInputArgs extends DtoBaseModel {

	/**
	 * 房屋所属用户
	 */
	private String userId;
	/**
	 * 所在城市
	 */
	private String cityId;
	/**
	 * 小区名称
	 */
	private String districtName;
	/**
	 * 住宅类型：公寓，复式，别墅
	 */
	private String houseTypeId;
	/**
	 * 房屋面积
	 */
	private Double area;
	/**
	 * 装修类型：简装、精装、旧房改造
	 */
	private String decorateTypeId;
	/**
	 * 装修预算
	 */
	private Long decorateBudget;
	/**
	 * 交房日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date launchDate;
	/**
	 * 装修日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date decorateDate;
	

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getHouseTypeId() {
		return houseTypeId;
	}
	public void setHouseTypeId(String houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getDecorateTypeId() {
		return decorateTypeId;
	}
	public void setDecorateTypeId(String decorateTypeId) {
		this.decorateTypeId = decorateTypeId;
	}
	public Long getDecorateBudget() {
		return decorateBudget;
	}
	public void setDecorateBudget(Long decorateBudget) {
		this.decorateBudget = decorateBudget;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public Date getDecorateDate() {
		return decorateDate;
	}
	public void setDecorateDate(Date decorateDate) {
		this.decorateDate = decorateDate;
	}
}
