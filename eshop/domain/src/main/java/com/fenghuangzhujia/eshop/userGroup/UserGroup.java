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
	private long minExp;
	private long maxExp;
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
	 * 最小经验值	
	 * @return
	 */
	public long getMinExp() {
		return minExp;
	}
	public void setMinExp(long minExp) {
		this.minExp = minExp;
	}
	
	/**
	 * 最大经验值
	 * @return
	 */
	public long getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(long maxExp) {
		this.maxExp = maxExp;
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
