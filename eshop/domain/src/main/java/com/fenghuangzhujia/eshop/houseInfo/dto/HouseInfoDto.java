package com.fenghuangzhujia.eshop.houseInfo.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;

public class HouseInfoDto extends DtoBaseModel {

	/**
	 * 房屋所属用户
	 */
	private UserDto user;
	/**
	 * 所在城市
	 */
	private AreaDto city;
	/**
	 * 小区名称
	 */
	private String districtName;
	/**
	 * 住宅类型：公寓，复式，别墅
	 */
	private CategoryItemDto houseType;
	/**
	 * 房屋面积
	 */
	private Double area;
	/**
	 * 装修类型：简装、精装、旧房改造
	 */
	private CategoryItemDto decorateType;
	/**
	 * 装修预算
	 */
	private Long decorateBudget;
	/**
	 * 交房日期
	 */
	private Date launchDate;
	/**
	 * 装修日期
	 */
	private Date decorateDate;
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public AreaDto getCity() {
		return city;
	}
	public void setCity(AreaDto city) {
		this.city = city;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public CategoryItemDto getHouseType() {
		return houseType;
	}
	public void setHouseType(CategoryItemDto houseType) {
		this.houseType = houseType;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public CategoryItemDto getDecorateType() {
		return decorateType;
	}
	public void setDecorateType(CategoryItemDto decorateType) {
		this.decorateType = decorateType;
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
