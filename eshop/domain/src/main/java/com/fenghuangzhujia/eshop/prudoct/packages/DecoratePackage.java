package com.fenghuangzhujia.eshop.prudoct.packages;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

/**
 * 装修套餐
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_decorate_package")
public class DecoratePackage extends UUIDBaseModel {
	/**套餐价格(元/m2)*/
	private Double price;
	/**套餐名称*/
	private String name;
	/**订金（元）*/
	private Double deposit;
	private String description;
	/**库存*/
	private Long inStock;
	/**已售出数量*/
	private Long saleNumber;
	/**装修状况，如新居，旧房改造*/
	private String decorate;
	private MediaContent mainPic;
	private Date scrambleStartTime;
	private Date scrambleEndTime;
	private ScrambleStatus status;

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getInStock() {
		return inStock;
	}
	public void setInStock(Long inStock) {
		this.inStock = inStock;
	}

	public Long getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(Long saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getDecorate() {
		return decorate;
	}
	public void setDecorate(String decorate) {
		this.decorate = decorate;
	}

	@OneToOne
	public MediaContent getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContent mainPic) {
		this.mainPic = mainPic;
	}

	public Date getScrambleStartTime() {
		return scrambleStartTime;
	}
	public void setScrambleStartTime(Date scrambleStartTime) {
		this.scrambleStartTime = scrambleStartTime;
	}

	public Date getScrambleEndTime() {
		return scrambleEndTime;
	}
	public void setScrambleEndTime(Date scrambleEndTime) {
		this.scrambleEndTime = scrambleEndTime;
	}

	public ScrambleStatus getStatus() {
		return status;
	}
	public void setStatus(ScrambleStatus status) {
		this.status = status;
	}

	/**套餐抢购状态*/
	public static enum ScrambleStatus {
		PREPARE,SCRAMBLE,FINISH;
	}
}
