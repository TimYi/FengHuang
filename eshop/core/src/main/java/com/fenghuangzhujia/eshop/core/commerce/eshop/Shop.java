package com.fenghuangzhujia.eshop.core.commerce.eshop;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.commerce.account.ShopAccount;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 线上商店，实现此接口的实体被认为是系统中的商店，可以卖东西
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_shop")
public class Shop extends UUIDBaseModel {
	
	private User owner;
	private String name;	
	private boolean verified;
	//private ShopType type;
	//private Set<ShopAccount> accounts;
	
	/**
	 * 店铺所有者账户
	 */
	@OneToOne
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	/**
	 * 店铺名称
	 * @return
	 */
	@Column(unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 用于判断店铺是否通过认证
	 * @return
	 */
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	/*
	 * 店铺类型，用于区分是自营还是个体店铺。
	 * @return
	 */
	/*
	@ManyToOne
	public ShopType getType() {
		return type;
	}
	public void setType(ShopType type) {
		this.type = type;
	}*/
	
	/*
	 * 店铺账户，店铺可以有多种账户，每种账户限有一个账户。
	 * 店铺至少需要支持一种账户类型
	 */
	/*
	@OneToMany(mappedBy="shop")
	public Set<ShopAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<ShopAccount> accounts) {
		this.accounts = accounts;
	}
	*/
}
