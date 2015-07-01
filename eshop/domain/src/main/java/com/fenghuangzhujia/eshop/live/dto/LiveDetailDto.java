package com.fenghuangzhujia.eshop.live.dto;

import java.util.Date;
import java.util.Set;

import com.fenghuangzhujia.eshop.worker.dto.WorkerDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class LiveDetailDto extends DtoBaseModel {

	/**直播地几天*/
	private int day;
	/**日期*/
	private Date date;
	/**施工项目*/
	private String title;
	/**温馨提示，对项目的解释*/
	private String tips;
	/**当日施工人员*/
	private Set<WorkerDto> workers;
	/**施工图片*/
	private Set<MediaContentDto> pics;
	/**微信互动截图*/
	private Set<MediaContentDto> interactPics;
	
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
	public Set<WorkerDto> getWorkers() {
		return workers;
	}
	public void setWorkers(Set<WorkerDto> workers) {
		this.workers = workers;
	}
	public Set<MediaContentDto> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContentDto> pics) {
		this.pics = pics;
	}
	public Set<MediaContentDto> getInteractPics() {
		return interactPics;
	}
	public void setInteractPics(Set<MediaContentDto> interactPics) {
		this.interactPics = interactPics;
	}
}
