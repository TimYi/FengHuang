package com.fenghuangzhujia.eshop.product;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.fenghuangzhujia.eshop.core.brand.Brand;
import com.fenghuangzhujia.eshop.core.column.Column;
import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

/**
 * 装修套餐
 * @author pc
 *
 */
@Entity
public class DecoratingPackage extends UUIDBaseModel {
	private Column column;
	private Brand brand;
	private Shop shop;
	private String title;
	private String goodsid;
	private Integer orderid;
	private String description;
	private String content;
	private MediaContent mainPic;
	private MediaContent thumbnails;
	private Set<MediaContent> pics;
	
	/**
	 * 栏目
	 * @return
	 */
	@ManyToOne
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	
	/**
	 * 品牌
	 * @return
	 */
	@ManyToOne
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	/**
	 * 所属商家
	 * @return
	 */
	@ManyToOne
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	/**
	 * 套餐名称
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 货号？
	 * @return
	 */
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	
	/**
	 * 排序字段
	 * @return
	 */
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	/**
	 * 摘要
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 描述内容
	 * @return
	 */
	@Type(type="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 商品主图
	 * @return
	 */
	@OneToOne
	public MediaContent getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContent mainPic) {
		this.mainPic = mainPic;
	}
	
	/**
	 * 缩略图
	 * @return
	 */
	@OneToOne
	public MediaContent getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(MediaContent thumbnails) {
		this.thumbnails = thumbnails;
	}
	
	/**
	 * 组图
	 * @return
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable
	public Set<MediaContent> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContent> pics) {
		this.pics = pics;
	}
}
