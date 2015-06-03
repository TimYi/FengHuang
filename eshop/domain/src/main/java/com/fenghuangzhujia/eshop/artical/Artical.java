package com.fenghuangzhujia.eshop.artical;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fenghuangzhujia.eshop.core.menu.Menu;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_artical")
public class Artical extends UUIDBaseModel {

	private Menu menu;
	private String title;
	private String source;
	private String sourceLink;
	private String author;
	private String keywords;
	private String description;
	private String content;
	private MediaContent mainPic;
	private MediaContent thumbnails;
	private int hits;
	private Set<MediaContent> pics;
	
	@ManyToOne
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
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
	 * 文章来源名称
	 * @return
	 */
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 文章来源地址
	 * @return
	 */
	public String getSourceLink() {
		return sourceLink;
	}
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}
	/**
	 * 文章作者
	 * @return
	 */
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 文章关键词
	 * @return
	 */
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 摘要
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 文章正文，为富文本
	 * @return
	 */
	@Type(type="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 文章主图
	 * @return
	 */
	@OneToOne
	public MediaContent getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContent mainPic) {
		this.mainPic = mainPic;
	}
	/**
	 * 文章缩略图
	 * @return
	 */
	@OneToOne
	public MediaContent getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(MediaContent thumbnails) {
		this.thumbnails = thumbnails;
	}
	/**
	 * 文章点击数
	 * @return
	 */
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	/**
	 * 轮播图
	 * @return
	 */
	@ManyToMany
	@JoinTable(name="fhzj_artial_pics")
	public Set<MediaContent> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContent> pics) {
		this.pics = pics;
	}
}
