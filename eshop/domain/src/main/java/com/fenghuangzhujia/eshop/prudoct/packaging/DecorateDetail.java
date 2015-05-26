package com.fenghuangzhujia.eshop.prudoct.packaging;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_decorate_detail")
public class DecorateDetail extends UUIDBaseModel {
	private int ordernum;
	private String position;
	private String meterial;
	private String count;
	private String description;
	private DecoratingPackage decoratingPackage;
	
	/**
	 * 序号
	 * @return
	 */
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	
	/**
	 * 位置
	 * @return
	 */
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 材料
	 * @return
	 */
	public String getMeterial() {
		return meterial;
	}
	public void setMeterial(String meterial) {
		this.meterial = meterial;
	}
	
	/**
	 * 数量
	 * @return
	 */
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	/**
	 * 详细信息
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 所属装修套餐
	 * @return
	 */
	@ManyToOne(optional=false)
	public DecoratingPackage getDecoratingPackage() {
		return decoratingPackage;
	}
	public void setDecoratingPackage(DecoratingPackage decoratingPackage) {
		this.decoratingPackage = decoratingPackage;
	}
	
	
}
