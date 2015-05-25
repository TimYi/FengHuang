package com.fenghuangzhujia.eshop.core.brand;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_brand")
public class Brand extends UUIDBaseModel {
	private String name;
	private Brand superBrand;
	private Set<Brand> subBrands;
	private BrandType brandType;
	private int priority;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	public Brand getSuperBrand() {
		return superBrand;
	}
	public void setSuperBrand(Brand superBrand) {
		this.superBrand = superBrand;
	}
	
	@OneToMany(mappedBy="superBrand")
	public Set<Brand> getSubBrands() {
		return subBrands;
	}
	public void setSubBrands(Set<Brand> subBrands) {
		this.subBrands = subBrands;
	}
	
	/**
	 * 品牌类别
	 * @return
	 */
	@ManyToOne(optional=false)
	public BrandType getBrandType() {
		return brandType;
	}
	public void setBrandType(BrandType brandType) {
		this.brandType = brandType;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
