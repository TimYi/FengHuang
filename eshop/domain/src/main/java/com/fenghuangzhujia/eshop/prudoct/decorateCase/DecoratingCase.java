package com.fenghuangzhujia.eshop.prudoct.decorateCase;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_decorating_case")
public class DecoratingCase extends UUIDBaseModel {
	private String title;
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
	 * 主图
	 * @return
	 */
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
	
}
