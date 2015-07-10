package com.fenghuangzhujia.eshop.materialManage.product;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.materialManage.brand.Brand;
import com.fenghuangzhujia.eshop.materialManage.material.Material;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 主材品牌旗下产品
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_material_product")
public class Product extends UUIDBaseModel {

	/**产品名称*/
	private String name;
	/**品牌*/
	private Brand brand;
	/**排序序号*/
	private int ordernum;
	/**品牌主材展示*/
	private Set<Material> materials;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(optional=false)
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL)
	@OrderBy("ordernum")
	public Set<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}
}
