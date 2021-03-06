package com.fenghuangzhujia.eshop.comment;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_comment")
public class Comment extends UUIDBaseModel {

	private String title;
	private String column;
	private String url;
	private String sourceid;
	private String parentid;
	private String categoryid;
	private String ownerid;
	private String metadata;
	private ResourceType type;
	private Set<CommentItem> comments;
	
	/**
	 * 文章标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 评论针对栏目
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
	 * 连接
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 来源id，凭借此来源获取评论
	 * @return
	 */
	@Column(unique=true,nullable=false)
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	/**
	 * 父栏目id
	 * @return
	 */
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * 文章所属频道id
	 * @return
	 */
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	/**
	 * 文章发布者id，可留空
	 * @param ownerid
	 */
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	/**
	 * 其它信息
	 * @return
	 */
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	/**
	 * 具体评论内容
	 * @return
	 */
	@OneToMany(mappedBy="comment",cascade=CascadeType.ALL)
	@OrderBy("createTime asc")
	public Set<CommentItem> getComments() {
		return comments;
	}
	public void setComments(Set<CommentItem> comments) {
		this.comments = comments;
	}
	@Enumerated
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
}
