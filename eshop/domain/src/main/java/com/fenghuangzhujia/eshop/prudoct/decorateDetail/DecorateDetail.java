package com.fenghuangzhujia.eshop.prudoct.decorateDetail;

import javax.persistence.Embeddable;

@Embeddable
public class DecorateDetail {
	private int ordernum;
	private String position;
	private String meterial;
	private String count;
	private String description;
	
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
}
