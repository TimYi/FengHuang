package com.fenghuangzhujia.eshop.core.event.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fenghuangzhujia.eshop.core.event.core.EventConfig;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
public abstract class EventConfigEntity extends UUIDBaseModel implements EventConfig {

	private String type;
	private String eventType;
	private String name;

	@Column(name="type",updatable=false,insertable=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	/**
	 * 事件名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
