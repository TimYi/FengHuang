package com.fenghuangzhujia.eshop.core.commerce.account;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 用户账户
 * @author pc
 *
 */
//@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="account_type")
public abstract class Account extends UUIDBaseModel {
	
	private AccountType type;

	/**
	 * 账户类型
	 * @return
	 */
	@ManyToOne
	public AccountType getType() {
		return this.type;
	}	
	public void setType(AccountType type) {
		this.type = type;
	}
}
