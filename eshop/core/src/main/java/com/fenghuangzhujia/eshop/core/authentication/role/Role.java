package com.fenghuangzhujia.eshop.core.authentication.role;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
public class Role extends UUIDBaseModel {
	
	private String name;
	private String description;
	private Set<Authority> authorities;
	private Set<User> users;	
	

	/**
	 * @return the name
	 */
	@Column(unique=true)
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
	 * @return the users
	 */
	@ManyToMany
	@JoinTable(name="user_roles")
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @return the authorities
	 */
	@ManyToMany
	@JoinTable(name="role_authorities")
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	
	private String[] authorityids;

	/**
	 * 保存时方便传值
	 * @return the authorityids
	 */
	@Transient
	public String[] getAuthorityids() {
		return authorityids;
	}

	/**
	 * @param authorityids the authorityids to set
	 */
	public void setAuthorityids(String[] authorityids) {
		this.authorityids = authorityids;
	}	
	
	private String[] userids;


	/**
	 * @return the userids
	 */
	public String[] getUserids() {
		return userids;
	}

	/**
	 * @param userids the userids to set
	 */
	@Transient
	public void setUserids(String[] userids) {
		this.userids = userids;
	}
	
	public boolean hasAuthority(Authority authority) {
		if(authorities==null) return false;
		for (Authority basicAuthority : authorities) {
			if(basicAuthority.getId().equals(authority.getId())) {
				return true;
			}
		}
		return false;
	}
}
