package com.fenghuangzhujia.eshop.collect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.eshop.common.remind.impl.UnreadRemindEntity;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_collect")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Collect extends UnreadRemindEntity {

	private User user;
	private String name;
	private String column;	//要显示栏目名称，暂时不了解栏目名称是啥
	private String url;
	private String sourceid;
	private ResourceType type;
	private MediaContent mainPic;
	
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
	 * 栏目名称
	 * @return
	 */
	@Column(name="column_name")
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
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
	
	@Column(unique=true,nullable=false)
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	
	@Enumerated
	@Column(nullable=false)
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
}
