package com.fenghuangzhujia.eshop.collect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_collect")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Collect extends UUIDBaseModel {

	private User user;
	private String name;
	private String url;
	private String sourceid;
	
	/**
	 * 用户
	 * @return
	 */
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 收藏主图片
	 * @return
	 */
	public abstract MediaContent getMainPic();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(unique=true,nullable=false)
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	
	/**
	 * 通过type区分收藏类型
	 * @return
	 */
	public abstract String getType();
}
