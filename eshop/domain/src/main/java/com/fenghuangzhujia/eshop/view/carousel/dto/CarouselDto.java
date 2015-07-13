package com.fenghuangzhujia.eshop.view.carousel.dto;

import com.fenghuangzhujia.eshop.view.navigation.dto.NavigationVo;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class CarouselDto extends DtoBaseModel {

	/**所属页面*/
	private NavigationVo page;
	/**排序字段*/
	private int ordernum;
	/**背景图片*/
	private MediaContentDto pic;
	/**左侧标题*/
	private String title;
	/**左侧内容文字*/
	private String content;
	/**按钮文字*/
	private String buttonText;
	/**button链接*/
	private String buttonUrl;
	/**右侧富文本*/
	private String rightArea;
	
	public NavigationVo getPage() {
		return page;
	}
	public void setPage(NavigationVo page) {
		this.page = page;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public MediaContentDto getPic() {
		return pic;
	}
	public void setPic(MediaContentDto pic) {
		this.pic = pic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getButtonText() {
		return buttonText;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	public String getButtonUrl() {
		return buttonUrl;
	}
	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
	}
	public String getRightArea() {
		return rightArea;
	}
	public void setRightArea(String rightArea) {
		this.rightArea = rightArea;
	}
}
