package com.fenghuangzhujia.eshop.collect.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class CollectDto extends DtoBaseModel {
	
	private String name;
	private MediaContentDto mainPic;
	private String url;
	private String type;
	private String sourceid;
	
	@JsonIgnore
	private String userid;
	@JsonIgnore
	private MultipartFile mainPicFile;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MediaContentDto getMainPic() {
		return mainPic;
	}
	public void setMainPic(MediaContentDto mainPic) {
		this.mainPic = mainPic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public MultipartFile getMainPicFile() {
		return mainPicFile;
	}
	public void setMainPicFile(MultipartFile mainPicFile) {
		this.mainPicFile = mainPicFile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
}
