package com.fenghuangzhujia.eshop.core.schedual.activity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.schedual.Schedual;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 人员活跃时间，即工作时间
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_activity")
public class Activity extends UUIDBaseModel {
	
	private boolean isOccupied=false;
	private LocalTime startTime;
	private LocalTime endTime;	
	private LocalDate date;
	@JsonIgnore
	private Schedual schedual;
	
	/**
	 * @return the isOccupied
	 */
	public boolean isOccupied() {
		return isOccupied;
	}
	/**
	 * @param isOccupied the isOccupied to set
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * @return the schedual
	 */
	@ManyToOne(optional=false)
	public Schedual getSchedual() {
		return schedual;
	}
	/**
	 * @param schedual the schedual to set
	 */
	public void setSchedual(Schedual schedual) {
		this.schedual = schedual;
	}	
	
	/**
	 * 获取活动持续时间
	 * @return
	 */
	@Transient
	public double getHours() {
		double hours=getStartTime().until(getEndTime(), ChronoUnit.MINUTES)/60.0;
		System.out.println(hours);
		return hours;
	}
	
	/**
	 * 判断另外一个Active是否和自身冲突
	 * @param active
	 * @return 如果有时间冲突，返回true，如果没有时间冲突，返回true
	 */
	public boolean conflict(Activity active) {
		if(!this.date.equals(active.date)) {
			return false;
		}
		//一个活动的开始时间处于另外一个活动的开始时间和结束时间之间
		if(conflict(this.startTime,active.startTime,active.endTime))return true;
		if(conflict(this.endTime,active.startTime,active.endTime))return true;
		if(conflict(active.startTime, startTime, endTime))return true;
		if(conflict(active.endTime, startTime, endTime))return true;
		return false;
	}
	
	/**
	 * 如果一个活动的开始或者结束时间介于另一个活动的开始或者结束时间之间，则时间冲突
	 * @param toCheck
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	protected boolean conflict(LocalTime toCheck, LocalTime startTime, LocalTime endTime) {
		return (toCheck.compareTo(startTime)>0 && toCheck.compareTo(endTime)<0);
	}
	
	//可以提供任意可能占用活动的导览
}
