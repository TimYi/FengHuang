package com.fenghuangzhujia.eshop.core.menu;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

@Entity
@Table(name="fhzj_menu")
public class Menu extends UUIDBaseModel {
	private Menu father;
	private String name;
	private String description;
	private Integer priority;
	private Set<Menu> sons;
	private CategoryItem type;
	
	/**
	 * 父栏目
	 * @return
	 */
	@ManyToOne
	public Menu getFather() {
		return father;
	}
	public void setFather(Menu father) {
		this.father = father;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@OneToMany(mappedBy="father",cascade=CascadeType.ALL)
	public Set<Menu> getSons() {
		return sons;
	}
	public void setSons(Set<Menu> sons) {
		this.sons = sons;
	}
	
	@ManyToOne
	public CategoryItem getType() {
		return type;
	}
	public void setType(CategoryItem type) {
		this.type = type;
	}
}
