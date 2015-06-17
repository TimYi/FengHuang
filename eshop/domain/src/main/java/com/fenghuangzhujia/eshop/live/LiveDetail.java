package com.fenghuangzhujia.eshop.live;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.worker.Worker;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_live_detail")
public class LiveDetail extends UUIDBaseModel {

	/**所属直播*/
	private Live live;
	/**直播地几天*/
	private int day;
	/**日期*/
	private Date date;
	/**施工项目*/
	private String title;
	/**温馨提示，对项目的解释*/
	private String tips;
	/**当日施工人员*/
	private Set<Worker> workers;
	/**施工图片*/
	private Set<MediaContent> pics;
	
	@ManyToOne(optional=false)
	public Live getLive() {
		return live;
	}
	public void setLive(Live live) {
		this.live = live;
	}
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
	@ManyToMany
	@JoinTable(name="fhzj_live_workers")
	public Set<Worker> getWorkers() {
		return workers;
	}
	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}
	@ManyToMany
	@JoinTable(name="fhzj_live_pics")
	public Set<MediaContent> getPics() {
		return pics;
	}
	public void setPics(Set<MediaContent> pics) {
		this.pics = pics;
	}
}
