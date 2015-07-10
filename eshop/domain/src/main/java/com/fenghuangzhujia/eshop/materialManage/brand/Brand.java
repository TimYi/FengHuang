package com.fenghuangzhujia.eshop.materialManage.brand;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.materialManage.product.Product;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_material_brand")
public class Brand extends UUIDBaseModel {
	/**品牌名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
	/**品牌logo*/
	private MediaContent logo;
	/**品牌旗下产品*/
	private Set<Product> products;
	
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
	public MediaContent getLogo() {
		return logo;
	}
	public void setLogo(MediaContent logo) {
		this.logo = logo;
	}
	@OneToMany(mappedBy="brand",cascade=CascadeType.ALL)
	@OrderBy("ordernum")
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
