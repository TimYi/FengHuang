package com.fenghuangzhujia.eshop.core.column;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

@Entity
@Table(name="column_def")
public class Column extends UUIDBaseModel {
	private Column father;
	private String name;
	private String description;
	private Integer priority;
	private Set<Column> sons;
	private CategoryItem type;
	
	/**
	 * 父栏目
	 * @return
	 */
	@ManyToOne
	public Column getFather() {
		return father;
	}
	public void setFather(Column father) {
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
	public Set<Column> getSons() {
		return sons;
	}
	public void setSons(Set<Column> sons) {
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
