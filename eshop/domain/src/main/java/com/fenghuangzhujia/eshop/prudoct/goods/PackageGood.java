package com.fenghuangzhujia.eshop.prudoct.goods;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCase;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;

@Entity
@Table(name="fhzj_decorat_good")
@DiscriminatorValue(value="DECORATE_PACKAGE")
public class PackageGood extends Good {
	
	private DecoratePackage decoratingPackage;
	private DecorateCase decorateCase;
	private Date appointTime;
	private Area area;
	private String address;
	private String remark;
	private Double houseArea;
	private String mobile;
	
	/**
	 * 购买的套餐
	 * @return
	 */
	@ManyToOne(optional=false)
	public DecoratePackage getDecoratingPackage() {
		return decoratingPackage;
	}
	public void setDecoratingPackage(DecoratePackage decoratingPackage) {
		this.decoratingPackage = decoratingPackage;
		if(decoratingPackage==null)return;
		if(decoratingPackage.getMarketPrice()!=null) {
			setPrice(decoratingPackage.getMarketPrice());
		} if(decoratingPackage.getSalePrice()!=null) {
			setRealPrice(decoratingPackage.getSalePrice());
		}
	}

	/**
	 * 选择的装修案例
	 * @return
	 */
	@ManyToOne
	public DecorateCase getDecorateCase() {
		return decorateCase;
	}
	public void setDecorateCase(DecorateCase decorateCase) {
		this.decorateCase = decorateCase;
	}
	
	/**
	 * 客户选择的装修时间
	 * @return
	 */
	public Date getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}

	/**
	 * 客户所在地区
	 * @return
	 */
	@ManyToOne
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * 客户详细地址
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 客户留言
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	@Transient
	public double getPrice() {
		return decoratingPackage.getMarketPrice();
	}	
	
	@Override
	@Transient
	public double getRealPrice() {
		return decoratingPackage.getSalePrice();
	}	
	
	@Override
	@Transient
	public String getMainPic() {
		return decoratingPackage.getMainPic().getUrl();
	}
	
	/**
	 * 房屋面积
	 * @return
	 */
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
