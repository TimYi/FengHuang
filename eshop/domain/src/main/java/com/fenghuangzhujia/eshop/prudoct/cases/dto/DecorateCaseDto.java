package com.fenghuangzhujia.eshop.prudoct.cases.dto;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.eshop.prudoct.detail.DecorateDetail;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecorateCaseDto extends DtoBaseModel {
	private String title;
	private String menuid;
	private String menu;
	private String brandid;
	private String brand;
	@JsonIgnore
	private MultipartFile mainPicFile;
	private MediaContentDto mainPic;
	@JsonIgnore
	private MultipartFile thumbnailsFile;
	private MediaContentDto thumbnails;
	@JsonIgnore
	private MultipartFile[] picFiles;
	private Set<MediaContentDto> pics;
	private Double houseArea;
	private Double totalPrice;
	@JsonIgnore
	private String categoryid;
	private CategoryItemDto category;
	@JsonIgnore
	private String styleid;
	private CategoryItemDto style;
	@JsonIgnore
	private String apartmentTypeId;
	private CategoryItemDto apartmentType;
	private String promise;
	private String description;
	private String keywords;
	private Set<DecorateDetail> details;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public MultipartFile getMainPicFile() {
		return mainPicFile;
	}
	public void setMainPicFile(MultipartFile mainPicFile) {
		this.mainPicFile = mainPicFile;
	}
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
		this.mainPic = mainPic;
	}
	public MultipartFile getThumbnailsFile() {
		return thumbnailsFile;
	}
	public void setThumbnailsFile(MultipartFile thumbnailsFile) {
		this.thumbnailsFile = thumbnailsFile;
	}
	public MediaContentDto getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(MediaContentDto thumbnails) {
		this.thumbnails = thumbnails;
	}
	public MultipartFile[] getPicFiles() {
		return picFiles;
	}
	public void setPicFiles(MultipartFile[] picFiles) {
		this.picFiles = picFiles;
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
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public CategoryItemDto getCategory() {
		return category;
	}
	public void setCategory(CategoryItemDto category) {
		this.category = category;
	}
	public String getStyleid() {
		return styleid;
	}
	public void setStyleid(String styleid) {
		this.styleid = styleid;
	}
	public CategoryItemDto getStyle() {
		return style;
	}
	public void setStyle(CategoryItemDto style) {
		this.style = style;
	}
	public String getApartmentTypeId() {
		return apartmentTypeId;
	}
	public void setApartmentTypeId(String apartmentTypeId) {
		this.apartmentTypeId = apartmentTypeId;
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
