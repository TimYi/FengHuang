package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.core.brand.dto.BrandVo;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuVo;
import com.fenghuangzhujia.eshop.prudoct.detail.DecorateDetail;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecoratePackageDto extends DtoBaseModel {

	private MenuVo menu;
	private BrandVo brand;
	private String title;
	private Double marketPrice;
	private Double salePrice;
	private Integer housenum;
	private boolean housewarn;
	private Integer warnnum;	
	private String description;
	private String content;
	private MediaContentDto mainPic;
	private MediaContentDto thumbnails;
	private Set<MediaContentDto> pics;	
	private CategoryItemDto houseType;
	private CategoryItemDto decorateType;
	private CategoryItemDto responseType;
	private Double workingDays;	
	private String keywrods;
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
	public CategoryItemDto getHouseType() {
		return houseType;
	}
	public void setHouseType(CategoryItemDto houseType) {
		this.houseType = houseType;
	}
	public CategoryItemDto getDecorateType() {
		return decorateType;
	}
	public void setDecorateType(CategoryItemDto decorateType) {
		this.decorateType = decorateType;
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
