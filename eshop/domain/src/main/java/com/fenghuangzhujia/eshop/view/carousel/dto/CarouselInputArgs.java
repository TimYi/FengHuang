package com.fenghuangzhujia.eshop.view.carousel.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CarouselInputArgs extends DtoBaseModel {

	/**所属页面*/
	private String pageId;
	/**排序字段*/
	private int ordernum;
	/**背景图片*/
	private MultipartFile picFile;
	/**左侧标题*/
	private String title;
	/**左侧内容文字*/
	private String content;
	/**按钮文字*/
	private String buttonText;
	/**button链接*/
	private String buttonUrl;
	/**右侧区域富文本*/
	private String rightArea;
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public MultipartFile getPicFile() {
		return picFile;
	}
	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
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
