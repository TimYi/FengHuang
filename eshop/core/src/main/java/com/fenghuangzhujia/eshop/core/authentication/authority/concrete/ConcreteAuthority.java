package com.fenghuangzhujia.eshop.core.authentication.authority.concrete;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fenghuangzhujia.eshop.core.authentication.authority.AbstractAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.authority.opration.OperationAuthority;

@Entity
@DiscriminatorValue(value="CONCRETE")
@Table(	name="fhzj_concrete_authority",
		uniqueConstraints=
		    @UniqueConstraint(columnNames={"operation", "resourceid"})
	)
public class ConcreteAuthority extends AbstractAuthority implements Authority {
	
	private OperationAuthority operation;
	private String resourceid;
	
	/**
	 * 操作类型
	 * @return
	 */
	@ManyToOne
	@JoinColumn(nullable=false,name="operation")
	public OperationAuthority getOperation() {
		return operation;
	}
	public void setOperation(OperationAuthority operation) {
		this.operation = operation;
	}
	
	/**
	 * 资源id
	 * @return
	 */
	@Column(nullable=false,name="resourceid")
	public String getResourceid() {
		return resourceid;
	}
	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}
	
	@Override
	@Transient
	public String getAuthority() {
		String operationName=getOperation().getAuthority();
		String authority=operationName+getResourceid();
		return authority;
	}


	//数据传输属性
	private String operationid;

	@Transient
	public String getOperationid() {
		return operationid;
	}
	public void setOperationid(String operationid) {
		this.operationid = operationid;
	}
}
