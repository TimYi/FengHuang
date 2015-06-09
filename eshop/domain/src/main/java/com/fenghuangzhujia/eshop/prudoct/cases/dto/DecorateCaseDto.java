package com.fenghuangzhujia.eshop.prudoct.cases.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.core.brand.dto.BrandVo;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuVo;
import com.fenghuangzhujia.eshop.prudoct.detail.DecorateDetail;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecorateCaseDto extends DtoBaseModel {
	private String title;
	private MenuVo menu;
	private BrandVo brand;
	private MediaContentDto mainPic;
	private MediaContentDto thumbnails;
	private Set<MediaContentDto> pics;
	private Double houseArea;
	private Double totalPrice;
	private CategoryItemDto category;
	private CategoryItemDto style;
	private CategoryItemDto apartmentType;
	private String promise;
	private String description;
	private String keywords;
	private Set<DecorateDetail> details;
	
	public MenuVo getMenu() {
		return menu;
	}
	public void setMenu(MenuVo menu) {
		this.menu = menu;
	}
	public BrandVo getBrand() {
		return brand;
	}
	public void setBrand(BrandVo brand) {
		this.brand = brand;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
		this.mainPic = mainPic;
	}
	public MediaContentDto getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(MediaContentDto thumbnails) {
		this.thumbnails = thumbnails;
	}
	public Set<MediaContentDto> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContentDto> pics) {
		this.pics = pics;
	}
	public Double getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public CategoryItemDto getCategory() {
		return category;
	}
	public void setCategory(CategoryItemDto category) {
		this.category = category;
	}
	public CategoryItemDto getStyle() {
		return style;
	}
	public void setStyle(CategoryItemDto style) {
		this.style = style;
	}
	public CategoryItemDto getApartmentType() {
		return apartmentType;
	}
	public void setApartmentType(CategoryItemDto apartmentType) {
		this.apartmentType = apartmentType;
	}
	public String getPromise() {
		return promise;
	}
	public void setPromise(String promise) {
		this.promise = promise;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Set<DecorateDetail> getDetails() {
		return details;
	}
	public void setDetails(Set<DecorateDetail> details) {
		this.details = details;
	}
}
