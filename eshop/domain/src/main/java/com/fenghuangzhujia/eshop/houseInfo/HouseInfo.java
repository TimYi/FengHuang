package com.fenghuangzhujia.eshop.houseInfo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

/**
 * 个人房屋信息
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_house_info")
public class HouseInfo extends UUIDBaseModel {

	/**
	 * 房屋所属用户
	 */
	private User user;
	/**
	 * 所在城市
	 */
	private Area city;
	/**
	 * 小区名称
	 */
	private String districtName;
	/**
	 * 住宅类型：公寓，复式，别墅
	 */
	private CategoryItem houseType;
	/**
	 * 房屋面积
	 */
	private Double area;
	/**
	 * 装修类型：简装、精装、旧房改造
	 */
	private CategoryItem decorateType;
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
	
	
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	public Area getCity() {
		return city;
	}
	public void setCity(Area city) {
		this.city = city;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@ManyToOne
	public CategoryItem getHouseType() {
		return houseType;
	}
	public void setHouseType(CategoryItem houseType) {
		this.houseType = houseType;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	@ManyToOne
	public CategoryItem getDecorateType() {
		return decorateType;
	}
	public void setDecorateType(CategoryItem decorateType) {
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
