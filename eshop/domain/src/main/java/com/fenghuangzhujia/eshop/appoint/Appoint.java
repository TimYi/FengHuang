package com.fenghuangzhujia.eshop.appoint;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.common.remind.impl.UnreadRemindEntity;
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
	private CategoryItem type;
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
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
