package com.fenghuangzhujia.eshop.collect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_collect")
public class Collect extends UUIDBaseModel {

	private User user;
	private String name;
	private MediaContent mainPic;
	private String url;
	private String type;
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
	@OneToOne
	public MediaContent getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContent mainPic) {
		this.mainPic = mainPic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 通过type区分收藏类型，不可为空
	 * @return
	 */
	@Column(nullable=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(unique=true,nullable=false)
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
}
