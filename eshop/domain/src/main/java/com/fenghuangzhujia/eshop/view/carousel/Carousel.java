package com.fenghuangzhujia.eshop.view.carousel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.templateEngine.fragment.TemplateFragment;
import com.fenghuangzhujia.eshop.view.navigation.Navigation;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_carousel")
public class Carousel extends UUIDBaseModel {

	/**所属页面*/
	private Navigation page;
	/**排序字段*/
	private int ordernum;
	/**背景图片*/
	private MediaContent pic;
	/**动态模板*/
	private TemplateFragment fragment;
	
	
	@ManyToOne
	public Navigation getPage() {
		return page;
	}
	public void setPage(Navigation page) {
		this.page = page;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	@OneToOne
	public MediaContent getPic() {
		return pic;
	}
	public void setPic(MediaContent pic) {
		this.pic = pic;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public TemplateFragment getFragment() {
		return fragment;
	}
	public void setFragment(TemplateFragment fragment) {
		this.fragment = fragment;
	}
}
