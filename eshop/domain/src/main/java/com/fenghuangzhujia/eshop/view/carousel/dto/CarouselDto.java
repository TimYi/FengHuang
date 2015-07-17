package com.fenghuangzhujia.eshop.view.carousel.dto;

import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentDto;
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
	/**动态模板*/
	private TemplateFragmentDto fragment;
	
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
	public TemplateFragmentDto getFragment() {
		return fragment;
	}
	public void setFragment(TemplateFragmentDto fragment) {
		this.fragment = fragment;
	}
}
