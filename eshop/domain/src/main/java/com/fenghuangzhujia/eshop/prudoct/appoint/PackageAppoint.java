package com.fenghuangzhujia.eshop.prudoct.appoint;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.utils.Java8TimeUtils;

/**
 * 套餐预约
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_package_appoint")
public class PackageAppoint extends UUIDBaseModel {

	private User user;
	private Area city;
	private DecoratePackage decoratePackage;
	private String realName;
	private String mobile;
	private boolean used;
	
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
	
	@ManyToOne
	public DecoratePackage getDecoratePackage() {
		return decoratePackage;
	}	
	public void setDecoratePackage(DecoratePackage decoratePackage) {
		this.decoratePackage = decoratePackage;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	
	@Transient
	public Date getExpireTime() {
		Date createTime=getCreateTime();
		LocalDate createDate=Java8TimeUtils.asLocalDate(createTime);
		Date expireDate=Java8TimeUtils.fromLocalDate(createDate.plusMonths(1));
		return expireDate;
	}
}
