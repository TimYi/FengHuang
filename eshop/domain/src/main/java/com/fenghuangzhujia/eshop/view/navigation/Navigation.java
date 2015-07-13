package com.fenghuangzhujia.eshop.view.navigation;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_navigation")
public class Navigation extends UUIDBaseModel {

	/**菜单类型*/
	private NavigationType type;
	/**菜单序号*/
	private int ordernum;
	/**菜单标题*/
	private String title;
	/**连接地址，只有URL类型的菜单有*/
	private String url;
	/**下级菜单，只有DROPDOWN类型菜单有*/
	private Set<Navigation> subNavigations;
	/**上级菜单*/
	private Navigation superNavigation;
	
	
	public NavigationType getType() {
		return type;
	}
	public void setType(NavigationType type) {
		this.type = type;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@OneToMany(mappedBy="superNavigation",cascade=CascadeType.ALL)
	public Set<Navigation> getSubNavigations() {
		return subNavigations;
	}
	public void setSubNavigations(Set<Navigation> subNavigations) {
		this.subNavigations = subNavigations;
	}
	@ManyToOne
	public Navigation getSuperNavigation() {
		return superNavigation;
	}
	public void setSuperNavigation(Navigation superNavigation) {
		this.superNavigation = superNavigation;
	}

	public static enum NavigationType {
		URL,DROPDOWN;
	}
}
