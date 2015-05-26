package com.fenghuangzhujia.eshop.core.authentication.authority.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.authentication.authority.AbstractAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.authority.opration.OperationAuthority;

/**
 * 资源类型，标识某种资源
 * 例如：订单资源、预约资源等，是粗粒度的权限
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_resource_authority")
@DiscriminatorValue("RESOURCE")
public class ResourceAuthority extends AbstractAuthority implements Authority {
	
	private String name;
	private String description;
	
	/**
	 * @return the name, unique, not null
	 */
	@Column(unique=true,nullable=false)
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
	
	@Override
	@Transient
	public String getAuthority() {
		return getName();
	}
	
	//导览属性
	private Set<OperationAuthority> operations;

	@OneToMany(mappedBy="resource",cascade=CascadeType.ALL)
	public Set<OperationAuthority> getOperations() {
		return operations;
	}

	public void setOperations(Set<OperationAuthority> operations) {
		this.operations = operations;
	}
}
