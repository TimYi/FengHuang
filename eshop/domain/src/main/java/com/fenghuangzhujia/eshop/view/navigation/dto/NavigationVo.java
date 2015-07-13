package com.fenghuangzhujia.eshop.view.navigation.dto;

import com.fenghuangzhujia.eshop.view.navigation.Navigation.NavigationType;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class NavigationVo extends DtoBaseModel {

	/**菜单类型*/
	private NavigationType type;
	/**菜单序号*/
	private int ordernum;
	/**菜单标题*/
	private String title;
	/**连接地址，只有URL类型的菜单有*/
	private String url;
	
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
}
