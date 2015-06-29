package com.fenghuangzhujia.eshop.core.remind.impl;

import javax.persistence.MappedSuperclass;

import com.fenghuangzhujia.eshop.core.remind.UnreadRemindModel;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@MappedSuperclass
public abstract class UnreadRemindEntity extends UUIDBaseModel implements UnreadRemindModel<String> {

	private boolean readed;

	public boolean getReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}
}
