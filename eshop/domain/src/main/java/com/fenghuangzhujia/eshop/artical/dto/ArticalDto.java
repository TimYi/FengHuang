package com.fenghuangzhujia.eshop.artical.dto;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.eshop.core.menu.dto.MenuVo;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class ArticalDto extends DtoBaseModel {

	@JsonIgnore
	private String menuid;
	private MenuVo menu;
	private String title;
	private String source;
	private String sourceLink;
	private String author;
	private String keywords;
	private String description;
	private String content;
	@JsonIgnore
	private MultipartFile mainPicFile;
	private MediaContentDto mainPic;
	@JsonIgnore
	private MultipartFile thumbnailsFile;
	private MediaContentDto thumbnails;
	private int hits;
	@JsonIgnore
	private MultipartFile[] picFiles;
	private Set<MediaContentDto> pics;
	
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public MenuVo getMenu() {
		return menu;
	}
	public void setMenu(MenuVo menu) {
		this.menu = menu;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceLink() {
		return sourceLink;
	}
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getMainPicFile() {
		return mainPicFile;
	}
	public void setMainPicFile(MultipartFile mainPicFile) {
		this.mainPicFile = mainPicFile;
	}
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
		this.mainPic = mainPic;
	}
	public MultipartFile getThumbnailsFile() {
		return thumbnailsFile;
	}
	public void setThumbnailsFile(MultipartFile thumbnailsFile) {
		this.thumbnailsFile = thumbnailsFile;
	}
	public MediaContentDto getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(MediaContentDto thumbnails) {
		this.thumbnails = thumbnails;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public MultipartFile[] getPicFiles() {
		return picFiles;
	}
	public void setPicFiles(MultipartFile[] picFiles) {
		this.picFiles = picFiles;
	}
	public Set<MediaContentDto> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContentDto> pics) {
		this.pics = pics;
	}
}
