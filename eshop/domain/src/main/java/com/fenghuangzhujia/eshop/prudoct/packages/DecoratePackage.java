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

	/**
	 * 需要根据时间节点和当前status判断返回什么样的status，分一下几种情况
	 * 1、status=PREPARE，如果当前时间处于抢购期，更改status为SCRAMBLE，如果当前时间超过截止时间，更改status为FINISHED
	 * 2、status=SCRAMBLE，如果当前时间超过截止时间，更改status为FINISHED，当重置抢购时间时，需要重置status
	 * 3、status=FINISH，如果当前时间小于抢购开始时间，更改status为PREPARE
	 * @return
	 */
	public ScrambleStatus getStatus() {
		//如果抢购开始或者截止时间没有设置，不做任何自动判断。
		if(scrambleStartTime==null || scrambleEndTime==null) {
			return status;
		}
		if(status==null) status=ScrambleStatus.PREPARE;
		Date now=new Date();
		if(status==ScrambleStatus.PREPARE) {
			if(now.after(scrambleStartTime) && now.before(scrambleEndTime)) {
				status=ScrambleStatus.SCRAMBLE;
			} else if(now.after(scrambleEndTime)) {
				status=ScrambleStatus.FINISH;
			}
		} else if(status==ScrambleStatus.SCRAMBLE) {
			if(now.after(scrambleEndTime)) {
				status=ScrambleStatus.FINISH;
			}
		} else if(status==ScrambleStatus.FINISH) {
			if(now.before(scrambleStartTime)) {
				status=ScrambleStatus.PREPARE;
			}
		}
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
