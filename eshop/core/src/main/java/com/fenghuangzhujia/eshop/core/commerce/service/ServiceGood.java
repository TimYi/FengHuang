package com.fenghuangzhujia.eshop.core.commerce.service;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.commerce.goods.Good;

@MappedSuperclass
public abstract class ServiceGood extends Good {
	@Transient
	public abstract Service getService();
}
