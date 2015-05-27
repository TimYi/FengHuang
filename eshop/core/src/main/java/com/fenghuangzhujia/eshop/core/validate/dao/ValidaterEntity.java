package com.fenghuangzhujia.eshop.core.validate.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fhzj_validater")
public class ValidaterEntity {
	
	private String id;
	private String code;
	private Date ExpireTime;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getExpireTime() {
		return ExpireTime;
	}
	public void setExpireTime(Date expireTime) {
		ExpireTime = expireTime;
	}
}
