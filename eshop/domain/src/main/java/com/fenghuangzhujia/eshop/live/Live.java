package com.fenghuangzhujia.eshop.live;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 可以随意修改命名~
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_live")
public class Live extends UUIDBaseModel {

	/**直播所属用户*/
	private User user;
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
	private ProjectProgress status;
	private Set<LiveDetail> lives;
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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
	@OneToMany(mappedBy="live")
	public Set<LiveDetail> getLives() {
		return lives;
	}
	public void setLives(Set<LiveDetail> lives) {
		this.lives = lives;
	}	
	
	public static enum ProjectProgress {
		进行中,
		已交房,
		验收中;
	}
}
