package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import java.util.Date;
import java.util.List;

import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage.ScrambleStatus;
import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateSpaceDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecoratePackageDto extends DtoBaseModel {

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
	private MediaContentDto mainPic;
	private Date scrambleStartTime;
	private Date scrambleEndTime;
	private ScrambleStatus status;
	private boolean hasAppointed;
	private boolean couldAppoint;
	private String reasonForCantAppoint;
	/**用户是否已经抢购此套餐*/
	private boolean hasScrambled;
	/**装修空间详情*/
	private List<DecorateSpaceDto> spaces;
	/**套餐对用某个用户的声明周期*/
	private PackageLifeCycle lifeCycle;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public List<DecorateSpaceDto> getSpaces() {
		return spaces;
	}
	public void setSpaces(List<DecorateSpaceDto> spaces) {
		this.spaces = spaces;
	}
	public PackageLifeCycle getLifeCycle() {
		return lifeCycle;
	}
	public void setLifeCycle(PackageLifeCycle lifeCycle) {
		this.lifeCycle = lifeCycle;
	}

	public static enum PackageLifeCycle {
		/**用户没有预约，并且可以预约，就让用户去预约*/
		APPOINT,
		/**用户已经预约，套餐抢购尚未开始*/
		WAITING,
		/**用户已经预约，套餐也可以抢购*/
		SCRAMBLE,
		/**用户不能够预约，套餐抢购结束*/
		FINISHED,
		/**用户没有可用预约，且不能再次预约，且已经抢购一个套餐并且没有支付，让用户去支付*/
		PAY,
		/**用户完成了预约抢购支付全部内容，并且没有可以用预约，也无法进行下一次预约*/
		COMPLETE,
	}
}
