package com.fenghuangzhujia.foundation.dics.dto;

import java.util.Set;

import com.fenghuangzhujia.foundation.core.entity.Identified;

public class CategoryDTO implements Identified<String> {
	private String id;
	private String parentType;
	private String name;
	private String remark;
	private Set<CategoryItemDto> items;
	private String type;
	private String parentid;
	private Set<CategoryDTO> subCategories;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the parentType
	 */
	public String getParentType() {
		return parentType;
	}
	/**
	 * @param parentType the parentType to set
	 */
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the items
	 */
	public Set<CategoryItemDto> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(Set<CategoryItemDto> items) {
		this.items = items;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the parentid
	 */
	public String getParentid() {
		return parentid;
	}
	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * @return the subCategories
	 */
	public Set<CategoryDTO> getSubCategories() {
		return subCategories;
	}
	/**
	 * @param subCategories the subCategories to set
	 */
	public void setSubCategories(Set<CategoryDTO> subCategories) {
		this.subCategories = subCategories;
	}
}
