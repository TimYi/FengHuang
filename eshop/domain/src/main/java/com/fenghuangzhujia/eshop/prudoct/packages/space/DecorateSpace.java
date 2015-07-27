package com.fenghuangzhujia.eshop.prudoct.packages.space;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_decorate_space")
public class DecorateSpace extends UUIDBaseModel {

	/**所属套餐*/
	private DecoratePackage decoratePackage;
	/**名称*/
	private String name;
	/**序号*/
	private int ordernum;
	/**第一张图片*/
	private MediaContent pic1;
	/**第二张图片*/
	private MediaContent pic2;
	/**第三张图片*/
	private MediaContent pic3;
	/**装修内容*/
	private Set<DecorateItem> items;
	
	@ManyToOne
	public DecoratePackage getDecoratePackage() {
		return decoratePackage;
	}
	public void setDecoratePackage(DecoratePackage decoratePackage) {
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
	@OneToOne
	public MediaContent getPic1() {
		return pic1;
	}
	public void setPic1(MediaContent pic1) {
		this.pic1 = pic1;
	}
	@OneToOne
	public MediaContent getPic2() {
		return pic2;
	}
	public void setPic2(MediaContent pic2) {
		this.pic2 = pic2;
	}
	@OneToOne
	public MediaContent getPic3() {
		return pic3;
	}
	public void setPic3(MediaContent pic3) {
		this.pic3 = pic3;
	}
	@OneToMany(mappedBy="space")
	public Set<DecorateItem> getItems() {
		return items;
	}
	public void setItems(Set<DecorateItem> items) {
		this.items = items;
	}
}
