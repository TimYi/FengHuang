package com.fenghuangzhujia.eshop.view.carousel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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
	/**左侧标题*/
	private String title;
	/**左侧内容文字*/
	private String content;
	/**按钮文字*/
	private String buttonText;
	/**button链接*/
	private String buttonUrl;
	/**右侧富文本*/
	private String right;
	
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
	@Type(type="text")
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
}
