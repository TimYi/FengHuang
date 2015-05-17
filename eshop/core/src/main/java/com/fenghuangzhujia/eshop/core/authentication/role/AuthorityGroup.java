package com.fenghuangzhujia.eshop.core.authentication.role;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 权限分组，仅用于显示方便。
 * 可以将权限在概念上分组，但是并不会真正参与到权限决策中。
 * @author pc
 *
 */
@Entity
public class AuthorityGroup extends UUIDBaseModel {
	
	private String name;
	private String description;
	private Set<Authority> authorities;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the authorities
	 */
	@OneToMany(mappedBy="group")
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
}
