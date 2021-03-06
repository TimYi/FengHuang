package com.fenghuangzhujia.eshop.cases.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.cases.CaseTag;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecorateCaseDto extends DtoBaseModel {
	/**案例名称*/
	private String name;
	/**套餐名称*/
	private String packageName;
	/**装修风格，如美式，简约*/
	private String style;
	/**空间，如厨房*/
	private String space;
	/**户型，如二居室*/
	private String houseType;
	/**总价格*/
	private Double price;
	/**面积*/
	private Double area;
	/**描述*/
	private String description;
	/**案例编号，手动录入*/
	private String code;
	/**案例主图*/
	private MediaContentDto mainPic;
	/**案例标签*/
	private Set<CaseTag> tags;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
		this.mainPic = mainPic;
	}
	public Set<CaseTag> getTags() {
		return tags;
	}
	public void setTags(Set<CaseTag> tags) {
		this.tags = tags;
	}
}
