package com.fenghuangzhujia.eshop.core.brand;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 品牌类型
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_brand_type")
public class BrandType extends UUIDBaseModel {
	private String name;
	private BrandType superType;
	private Set<BrandType> subTypes;
	private int priority;
	
	/**
	 * 类别名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 上级类型
	 * @return
	 */
	@ManyToOne
	public BrandType getSuperType() {
		return superType;
	}
	public void setSuperType(BrandType superType) {
		this.superType = superType;
	}
	
	/**
	 * 子类型
	 * @return
	 */
	@OneToMany(mappedBy="superType")
	public Set<BrandType> getSubTypes() {
		return subTypes;
	}
	public void setSubTypes(Set<BrandType> subTypes) {
		this.subTypes = subTypes;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
