package com.fenghuangzhujia.eshop.core.authentication.role;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.authentication.authority.concrete.ConcreteAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.opration.OperationAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.resource.ResourceAuthority;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
public class Role extends UUIDBaseModel {
	
	private String name;
	private String description;
	private Set<ResourceAuthority> resourceAuthorities;
	private Set<OperationAuthority> operationAuthorities;
	private Set<ConcreteAuthority> concreteAuthorities;
	private Set<User> users;
	
	@Column(unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany
	@JoinTable
	public Set<ResourceAuthority> getResourceAuthorities() {
		return resourceAuthorities;
	}
	public void setResourceAuthorities(Set<ResourceAuthority> resourceAuthorities) {
		this.resourceAuthorities = resourceAuthorities;
	}
	
	@ManyToMany
	@JoinTable
	public Set<OperationAuthority> getOperationAuthorities() {
		return operationAuthorities;
	}
	public void setOperationAuthorities(Set<OperationAuthority> operationAuthorities) {
		this.operationAuthorities = operationAuthorities;
	}
	
	@ManyToMany
	@JoinTable
	public Set<ConcreteAuthority> getConcreteAuthorities() {
		return concreteAuthorities;
	}
	public void setConcreteAuthorities(Set<ConcreteAuthority> concreteAuthorities) {
		this.concreteAuthorities = concreteAuthorities;
	}
	
	@ManyToMany
	@JoinTable
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}	
	
	//数据传输属性
	private String[] userids;
	private String[] resourceids;
	private String[] operationids;
	private String[] authorityids;

	@Transient
	public String[] getUserids() {
		return userids;
	}
	public void setUserids(String[] userids) {
		this.userids = userids;
	}
	@Transient
	public String[] getResourceids() {
		return resourceids;
	}
	public void setResourceids(String[] resourceids) {
		this.resourceids = resourceids;
	}
	@Transient
	public String[] getOperationids() {
		return operationids;
	}
	public void setOperationids(String[] operationids) {
		this.operationids = operationids;
	}
	@Transient
	public String[] getAuthorityids() {
		return authorityids;
	}
	public void setAuthorityids(String[] authorityids) {
		this.authorityids = authorityids;
	}
}
