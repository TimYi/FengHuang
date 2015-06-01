package com.fenghuangzhujia.eshop.coupons.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.apache.commons.lang.NotImplementedException;

import com.fenghuangzhujia.eshop.coupons.core.CouponsStragy;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_couponse_stragy")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@DiscriminatorValue(value="ABSTRACT")
public class CouponsStragyEntity extends UUIDBaseModel implements CouponsStragy {

	private String type;
	private String name;
	
	@Override
	@Column(name="type",insertable=false,updatable=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Double getCoupons(Object[] args) {
		throw new NotImplementedException();
	}	
}
