package com.fenghuangzhujia.eshop.prudoct.packaging;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fenghuangzhujia.eshop.core.brand.Brand;
import com.fenghuangzhujia.eshop.core.column.Column;
import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.media.MediaContent;

/**
 * 装修套餐
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_decorating_package")
public class DecoratingPackage extends UUIDBaseModel {
	private Column column;
	private Brand brand;
	private Shop shop;
	private String title;
	private Double marketPrice;
	private Double salePrice;
	private Integer housenum;
	private boolean housewarn;
	private Integer warnnum;
	private Integer orderid;	
	private String description;
	private String content;
	private MediaContent mainPic;
	private MediaContent thumbnails;
	private Set<MediaContent> pics;	
	private CategoryItem houseType;
	private CategoryItem decorateType;
	private CategoryItem responseType;
	private Double workingDays;	
	private String keywrods;
	private Set<DecorateDetail> details;
	
	/**
	 * 栏目
	 * @return
	 */
	@ManyToOne
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	
	/**
	 * 品牌
	 * @return
	 */
	@ManyToOne
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	/**
	 * 所属商家
	 * @return
	 */
	@ManyToOne
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	/**
	 * 套餐名称
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 市场价格，元/m2
	 * @return
	 */
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	/**
	 * 销售价格，元/m2
	 * @return
	 */
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
	/**
	 * 库存数量
	 * @return
	 */
	public Integer getHousenum() {
		return housenum;
	}
	public void setHousenum(Integer housenum) {
		this.housenum = housenum;
	}
	
	/**
	 * 是否开启库存警告
	 * @return
	 */
	public boolean isHousewarn() {
		return housewarn;
	}
	public void setHousewarn(boolean housewarn) {
		this.housewarn = housewarn;
	}
	
	/**
	 * 警告数量
	 * @return
	 */
	public Integer getWarnnum() {
		return warnnum;
	}
	public void setWarnnum(Integer warnnum) {
		this.warnnum = warnnum;
	}
	
	/**
	 * 排序字段
	 * @return
	 */
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	/**
	 * 摘要
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 描述内容
	 * @return
	 */
	@Type(type="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 商品主图
	 * @return
	 */
	@OneToOne
	public MediaContent getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContent mainPic) {
		this.mainPic = mainPic;
	}
	
	/**
	 * 缩略图
	 * @return
	 */
	@OneToOne
	public MediaContent getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(MediaContent thumbnails) {
		this.thumbnails = thumbnails;
	}
	
	/**
	 * 组图
	 * @return
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable
	public Set<MediaContent> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContent> pics) {
		this.pics = pics;
	}
	
	/**
	 * 房屋类型
	 * @return
	 */
	public CategoryItem getHouseType() {
		return houseType;
	}
	public void setHouseType(CategoryItem houseType) {
		this.houseType = houseType;
	}
	
	/**
	 * 装修类型
	 * @return
	 */
	public CategoryItem getDecorateType() {
		return decorateType;
	}
	public void setDecorateType(CategoryItem decorateType) {
		this.decorateType = decorateType;
	}
	
	/**
	 * 全包、半包类型
	 * @return
	 */
	public CategoryItem getResponseType() {
		return responseType;
	}
	public void setResponseType(CategoryItem responseType) {
		this.responseType = responseType;
	}
	
	/**
	 * 工期
	 * @return
	 */
	public Double getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(Double workingDays) {
		this.workingDays = workingDays;
	}
	
	/**
	 * 关键字
	 * @return
	 */
	public String getKeywrods() {
		return keywrods;
	}
	public void setKeywrods(String keywrods) {
		this.keywrods = keywrods;
	}
	
	/**
	 * 装修细节
	 * @return
	 */
	@OneToMany(mappedBy="decoratingPackage",cascade=CascadeType.ALL)
	public Set<DecorateDetail> getDetails() {
		return details;
	}
	public void setDetails(Set<DecorateDetail> details) {
		this.details = details;
	}
}
