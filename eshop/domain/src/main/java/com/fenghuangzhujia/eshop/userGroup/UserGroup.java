package com.fenghuangzhujia.eshop.userGroup;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

@Entity
@Table(name="fhzj_user_group")
public class UserGroup extends UUIDBaseModel {

	private String name;
	private long expVal;
	private int stars;
	private CategoryItem color;
	
	/**
	 * 用户名
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 经验值
	 * @return
	 */
	public long getExpVal() {
		return expVal;
	}
	public void setExpVal(long expVal) {
		this.expVal = expVal;
	}
	
	/**
	 * 显示的星数
	 * @return
	 */
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	/**
	 * 头衔颜色
	 * @return
	 */
	@ManyToOne
	public CategoryItem getColor() {
		return color;
	}
	public void setColor(CategoryItem color) {
		this.color = color;
	}
}
