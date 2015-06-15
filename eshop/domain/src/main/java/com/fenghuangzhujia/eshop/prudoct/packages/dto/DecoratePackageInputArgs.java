package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage.ScrambleStatus;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class DecoratePackageInputArgs extends DtoBaseModel {

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
	private MultipartFile mainPicFile;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date scrambleStartTime;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date scrambleEndTime;
	private ScrambleStatus status;
	
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
	public MultipartFile getMainPicFile() {
		return mainPicFile;
	}
	public void setMainPicFile(MultipartFile mainPicFile) {
		this.mainPicFile = mainPicFile;
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
}
