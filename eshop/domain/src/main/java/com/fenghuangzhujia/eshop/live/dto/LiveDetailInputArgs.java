package com.fenghuangzhujia.eshop.live.dto;

import java.util.Date;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class LiveDetailInputArgs extends DtoBaseModel {

	/**直播地几天*/
	private int day;
	/**日期*/
	private Date date;
	/**施工项目*/
	private String title;
	/**温馨提示，对项目的解释*/
	private String tips;
	/**当日施工人员*/
	private Set<String> workerids;
	/**施工图片*/
	private Set<MultipartFile> picFiles;
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public Set<String> getWorkerids() {
		return workerids;
	}
	public void setWorkerids(Set<String> workerids) {
		this.workerids = workerids;
	}
	public Set<MultipartFile> getPicFiles() {
		return picFiles;
	}
	public void setPicFiles(Set<MultipartFile> picFiles) {
		this.picFiles = picFiles;
	}
}
