package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage.ScrambleStatus;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecoratePackageDto extends DtoBaseModel {

	/**套餐价格(元/m2)*/
	private Double price;
	/**订金（元）*/
	private Double deposit;
	private String description;
	/**库存*/
	private Long inStock;
	/**已售出数量*/
	private Long saleNumber;
	/**装修状况，如新居，旧房改造*/
	private String decorate;
	private MediaContentDto mainPic;
	private Date scrambleStartTime;
	private Date scrambleEndTime;
	private ScrambleStatus status;
	private boolean hasAppointed;
	private boolean couldAppoint;
	private String reasonForCantAppoint;
	/**用户是否已经抢购此套餐*/
	private boolean hasScrambled;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
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
	public boolean isHasAppointed() {
		return hasAppointed;
	}
	public void setHasAppointed(boolean hasAppointed) {
		this.hasAppointed = hasAppointed;
	}
	public boolean isCouldAppoint() {
		return couldAppoint;
	}
	public void setCouldAppoint(boolean couldAppoint) {
		this.couldAppoint = couldAppoint;
	}
	public String getReasonForCantAppoint() {
		return reasonForCantAppoint;
	}
	public void setReasonForCantAppoint(String reasonForCantAppoint) {
		this.reasonForCantAppoint = reasonForCantAppoint;
	}
	public boolean isHasScrambled() {
		return hasScrambled;
	}
	public void setHasScrambled(boolean hasScrambled) {
		this.hasScrambled = hasScrambled;
	}
}
