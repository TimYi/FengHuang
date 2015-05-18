package com.fenghuangzhujia.eshop.core.authentication.authority.opration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.authority.resource.ResourceAuthority;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 操作权限，针对某种资源的某种操作
 * @author pc
 *
 */
@Entity
@Table(
		//同种资源只能有同种操作
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"resource", "name"})
	)
public class OperationAuthority extends UUIDBaseModel implements Authority {
	
	private ResourceAuthority resource;
	private String name;	
	private String description;
	
	@ManyToOne
	@JoinColumn(nullable=false,name="resource")
	public ResourceAuthority getResource() {
		return resource;
	}

	public void setResource(ResourceAuthority resource) {
		this.resource = resource;
	}

	@Column(nullable=false,name="name")
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
	
	@Override
	@Transient
	public String getAuthority() {
		String resourceName=resource.getName();
		String authority=resourceName+":"+getName();
		return authority;
	}
	
	//数据传输属性
	private String resourceid;

	@Transient
	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}
}
