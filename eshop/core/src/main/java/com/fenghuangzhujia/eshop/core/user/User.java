package com.fenghuangzhujia.eshop.core.user;

import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
public class User extends UUIDBaseModel implements UserDetails {
	private static final long serialVersionUID = 8173403045355645001L;
	
	private String username;
	private String password;	
	private Set<Role> roles;
	private Boolean verified;
	/**
	 * @return the username
	 */
	@Column(name="account",unique=true,updatable=false)
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="verified")
	public Boolean isVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	/**
	 * @return the roles
	 */
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="users")
	public Set<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	/**
	 * 通过是否通过认证给予权限
	 */
	@Transient
	@Override
	public boolean isEnabled() {
		return verified;
	}	
	
	private Set<Authority> authorities;
	/**
	 * @return the authorities
	 * never <code>null</code>
	 */
	@Transient
	@Override
	public Set<Authority> getAuthorities() {
		return authorities==null?Collections.<Authority>emptySet():authorities;
	}
	/**
	 * 在认证之前，服务层要保证authorities被赋值
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	private String[] roleids;
	/**
	 * 方便保存时传值
	 * @return the roleids
	 */
	@Transient
	public String[] getRoleids() {
		return roleids;
	}
	/**
	 * @param roleids the roleids to set
	 */
	public void setRoleids(String[] roleids) {
		this.roleids = roleids;
	}
	
}
