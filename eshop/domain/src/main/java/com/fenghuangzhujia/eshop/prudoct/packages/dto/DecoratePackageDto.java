package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.eshop.prudoct.detail.DecorateDetail;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecoratePackageDto extends DtoBaseModel {
	private String menuid;
	private String menu;
	private String brandid;
	private String brand;
	private String title;
	private Double marketPrice;
	private Double salePrice;
	private Integer housenum;
	private boolean housewarn;
	private Integer warnnum;	
	private String description;
	private String content;
	@JsonIgnore
	private MultipartFile mainPicFile;
	private MediaContentDto mainPic;
	@JsonIgnore
	private MultipartFile thumbnailsFile;
	private MediaContentDto thumbnails;
	@JsonIgnore
	private MultipartFile[] picFiles;
	private Set<MediaContentDto> pics;	
	@JsonIgnore
	private String houseTypeId;
	private CategoryItemDto houseType;
	@JsonIgnore
	private String decorateTypeId;
	private CategoryItemDto decorateType;
	@JsonIgnore
	private String responseTypeId;
	private CategoryItemDto responseType;
	private Double workingDays;	
	private String keywrods;
	private Set<DecorateDetail> details;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getHousenum() {
		return housenum;
	}
	public void setHousenum(Integer housenum) {
		this.housenum = housenum;
	}
	public boolean isHousewarn() {
		return housewarn;
	}
	public void setHousewarn(boolean housewarn) {
		this.housewarn = housewarn;
	}
	public Integer getWarnnum() {
		return warnnum;
	}
	public void setWarnnum(Integer warnnum) {
		this.warnnum = warnnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getHouseTypeId() {
		return houseTypeId;
	}
	public void setHouseTypeId(String houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
	public CategoryItemDto getHouseType() {
		return houseType;
	}
	public void setHouseType(CategoryItemDto houseType) {
		this.houseType = houseType;
	}
	public String getDecorateTypeId() {
		return decorateTypeId;
	}
	public void setDecorateTypeId(String decorateTypeId) {
		this.decorateTypeId = decorateTypeId;
	}
	public CategoryItemDto getDecorateType() {
		return decorateType;
	}
	public void setDecorateType(CategoryItemDto decorateType) {
		this.decorateType = decorateType;
	}
	public String getResponseTypeId() {
		return responseTypeId;
	}
	public void setResponseTypeId(String responseTypeId) {
		this.responseTypeId = responseTypeId;
	}
	public CategoryItemDto getResponseType() {
		return responseType;
	}
	public void setResponseType(CategoryItemDto responseType) {
		this.responseType = responseType;
	}
	public Double getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(Double workingDays) {
		this.workingDays = workingDays;
	}
	public String getKeywrods() {
		return keywrods;
	}
	public void setKeywrods(String keywrods) {
		this.keywrods = keywrods;
	}
	public Set<DecorateDetail> getDetails() {
		return details;
	}
	public void setDetails(Set<DecorateDetail> details) {
		this.details = details;
	}
}
