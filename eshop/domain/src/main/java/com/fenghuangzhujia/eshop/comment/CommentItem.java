package com.fenghuangzhujia.eshop.comment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_comment_item")
public class CommentItem extends UUIDBaseModel {

	private String content;
	private CommentItem reply;
	private User user;
	private String ip;
	private Comment comment;
	
	/**
	 * 评论内容
	 * @return
	 */
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 回复的评论
	 * @return
	 */
	@OneToOne
	public CommentItem getReply() {
		return reply;
	}
	public void setReply(CommentItem reply) {
		this.reply = reply;
	}
	
	/**
	 * 发表评论的用户
	 * @return
	 */
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 用户ip地址
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * 所属评论
	 * @return
	 */
	@ManyToOne(optional=false)
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	@Transient
	public String getSourceid() {
		return comment.getSourceid();
	}
	
	@Transient
	public String getUrl() {
		return comment.getUrl();
	}
}
