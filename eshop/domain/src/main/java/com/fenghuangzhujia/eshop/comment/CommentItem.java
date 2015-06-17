package com.fenghuangzhujia.eshop.comment;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.eshop.common.remind.impl.UnreadRemindEntity;
import com.fenghuangzhujia.eshop.core.user.User;

@Entity
@Table(name="fhzj_comment_item")
public class CommentItem extends UnreadRemindEntity {

	private String content;
	private CommentItem reply;
	private User user;
	private String ip;
	private Comment comment;
	/**
	 * 回复次评论的评论
	 */
	private Set<CommentItem> replys;
	
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
	@ManyToOne
	public CommentItem getReply() {
		return reply;
	}
	public void setReply(CommentItem reply) {
		this.reply = reply;
	}
	
	@OneToMany(mappedBy="reply")
	public Set<CommentItem> getReplys() {
		return replys;
	}
	public void setReplys(Set<CommentItem> replys) {
		this.replys = replys;
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
	
	@Transient
	public String getColumn() {
		return comment.getColumn();
	}
	
	@Transient
	public ResourceType getType() {
		return comment.getType();
	}
}
