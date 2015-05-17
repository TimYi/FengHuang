package com.fenghuangzhujia.eshop.core.commerce.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

//@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class AccountType extends UUIDBaseModel {
	
	private String type;

	@Column(updatable=false,insertable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
