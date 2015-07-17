package com.fenghuangzhujia.eshop.view.carousel.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CarouselInputArgs extends DtoBaseModel {

	/**所属页面*/
	private String pageId;
	/**排序字段*/
	private int ordernum;
	/**背景图片*/
	private MultipartFile picFile;
	/**模板配置信息*/
	private TemplateFragmentInputArgs fragmentInfo;
	
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
	public TemplateFragmentInputArgs getFragmentInfo() {
		return fragmentInfo;
	}
	public void setFragmentInfo(TemplateFragmentInputArgs fragmentInfo) {
		this.fragmentInfo = fragmentInfo;
	}
}
