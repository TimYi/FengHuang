package com.fenghuangzhujia.eshop.appoint;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.common.remind.impl.UnreadRemindEntity;
import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

/**
 * 预约（验房、量房或者设计）
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_appoint")
public class Appoint extends UnreadRemindEntity {
	
	private User user;
	/**
	 * 城市，AreaLevel=city
	 */
	private Area city;
	/**
	 * 预约类型，type=appoint
	 */
	private CategoryItem type;
	private String realName;
	private String mobile;
	
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(optional=false)
	public CategoryItem getType() {
		return type;
	}
	public void setType(CategoryItem type) {
		this.type = type;
	}
	@ManyToOne(optional=false)
	public Area getCity() {
		return city;
	}
	public void setCity(Area city) {
		this.city = city;
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
}
