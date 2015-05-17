package com.fenghuangzhujia.eshop.core.schedual;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 代表对schedual的修改
 * @author pc
 *
 */
public class Operation {
	private boolean delete;
	private String id;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	
	/**
	 * if true，delete id对应的active
	 * if false，添加一个新active
	 * @return
	 */
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
}
