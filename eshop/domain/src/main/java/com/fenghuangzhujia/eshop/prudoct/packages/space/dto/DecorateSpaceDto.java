package com.fenghuangzhujia.eshop.prudoct.packages.space.dto;

import java.util.List;

import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageVo;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecorateSpaceDto extends DtoBaseModel {

	/**所属套餐*/
	private DecoratePackageVo decoratePackage;
	/**名称*/
	private String name;
	/**序号*/
	private int ordernum;
	/**第一张图片*/
	private MediaContentDto pic1;
	/**第二张图片*/
	private MediaContentDto pic2;
	/**第三张图片*/
	private MediaContentDto pic3;
	/**装修内容*/
	private List<DecorateItemDto> items;
	
	public DecoratePackageVo getDecoratePackage() {
		return decoratePackage;
	}
	public void setDecoratePackage(DecoratePackageVo decoratePackage) {
		this.decoratePackage = decoratePackage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public MediaContentDto getPic1() {
		return pic1;
	}
	public void setPic1(MediaContentDto pic1) {
		this.pic1 = pic1;
	}
	public MediaContentDto getPic2() {
		return pic2;
	}
	public void setPic2(MediaContentDto pic2) {
		this.pic2 = pic2;
	}
	public MediaContentDto getPic3() {
		return pic3;
	}
	public void setPic3(MediaContentDto pic3) {
		this.pic3 = pic3;
	}
	public List<DecorateItemDto> getItems() {
		return items;
	}
	public void setItems(List<DecorateItemDto> items) {
		this.items = items;
	}
}
