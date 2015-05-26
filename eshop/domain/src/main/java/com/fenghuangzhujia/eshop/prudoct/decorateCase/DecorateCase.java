package com.fenghuangzhujia.eshop.prudoct.decorateCase;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.brand.Brand;
import com.fenghuangzhujia.eshop.core.column.Column;
import com.fenghuangzhujia.eshop.prudoct.decorateDetail.DecorateDetail;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_decorate_case")
public class DecorateCase extends UUIDBaseModel {
	private String title;
	private Column column;
	private Brand brand;
	private MediaContent mainPic;
	private MediaContent thumbnails;
	private Set<MediaContent> pics;
	private Double houseArea;
	private Double totalPrice;
	private CategoryItem category;
	private CategoryItem style;
	private CategoryItem apartmentType;
	private String promise;
	private String description;
	private String keywords;
	private Set<DecorateDetail> details;
	
	/**
	 * 案例名称
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 所属栏目
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
	 * 所属品牌
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
	 * 主图
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
	 * 轮播组图
	 * @return
	 */
	@ManyToMany
	public Set<MediaContent> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContent> pics) {
		this.pics = pics;
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
	
	/**
	 * 总价格
	 * @return
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * 类型（如客厅）
	 * @return
	 */
	@ManyToOne
	public CategoryItem getCategory() {
		return category;
	}
	public void setCategory(CategoryItem category) {
		this.category = category;
	}
	
	/**
	 * 风格（如美式）
	 * @return
	 */
	@ManyToOne
	public CategoryItem getStyle() {
		return style;
	}
	public void setStyle(CategoryItem style) {
		this.style = style;
	}
	
	/**
	 * 户型（如三居室）
	 * @return
	 */
	@ManyToOne
	public CategoryItem getApartmentType() {
		return apartmentType;
	}
	public void setApartmentType(CategoryItem apartmentType) {
		this.apartmentType = apartmentType;
	}
	
	/**
	 * 承诺
	 * @return
	 */
	public String getPromise() {
		return promise;
	}
	public void setPromise(String promise) {
		this.promise = promise;
	}
	
	/**
	 * 描述
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 关键字
	 * @return
	 */
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * 装修详情
	 * @return
	 */
	@ElementCollection
	@CollectionTable(name="fhzj_decorate_case_details")
	public Set<DecorateDetail> getDetails() {
		return details;
	}
	public void setDetails(Set<DecorateDetail> details) {
		this.details = details;
	}
}
