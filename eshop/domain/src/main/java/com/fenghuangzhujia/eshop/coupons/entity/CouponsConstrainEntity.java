package com.fenghuangzhujia.eshop.coupons.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.coupons.core.CouponsConstrain;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * GENERIC类型限制，任何场景都可以使用
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_coupons_constrain")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@DiscriminatorValue(value="GENERIC")
public class CouponsConstrainEntity extends UUIDBaseModel implements CouponsConstrain {

	private String type;	
	private String name;	
	
	@Column(name="type",insertable=false,updatable=false)
	@Override
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
	
	@Transient
	@Override
	public boolean couldUse(Object[] args) {
		return true;
	}
}
