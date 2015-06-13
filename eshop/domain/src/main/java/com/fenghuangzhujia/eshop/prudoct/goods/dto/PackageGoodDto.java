package com.fenghuangzhujia.eshop.prudoct.goods.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.area.dto.AreaDto;
import com.fenghuangzhujia.eshop.core.commerce.goods.dto.GoodDto;
import com.fenghuangzhujia.eshop.prudoct.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;

public class PackageGoodDto extends GoodDto {
	private DecoratePackageDto decoratingPackage;
	private DecorateCaseDto decorateCase;
	private Date appointTime;
	private AreaDto area;
	private String address;
	private String remark;
	private Double houseArea;
	private String mobile;
	
	public DecoratePackageDto getDecoratingPackage() {
		return decoratingPackage;
	}
	public void setDecoratingPackage(DecoratePackageDto decoratingPackage) {
		this.decoratingPackage = decoratingPackage;
	}
	public DecorateCaseDto getDecorateCase() {
		return decorateCase;
	}
	public void setDecorateCase(DecorateCaseDto decorateCase) {
		this.decorateCase = decorateCase;
	}
	public Date getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}
	public AreaDto getArea() {
		return area;
	}
	public void setArea(AreaDto area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
