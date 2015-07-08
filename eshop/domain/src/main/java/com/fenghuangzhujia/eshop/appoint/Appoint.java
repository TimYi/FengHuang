package com.fenghuangzhujia.eshop.appoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.remind.impl.UnreadRemindEntity;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

/**
 * 预约（验房、量房或者设计）
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_appoint")
public class Appoint extends UnreadRemindEntity {
	
	
	public static final String TYPE_CODE="AP";
	
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
	/**易于记忆的唯一编码*/
	private String code;
	
	public Appoint() {
		code=CodeGenerater.generateCode(TYPE_CODE);
	}
	
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
	@Column(unique=true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
