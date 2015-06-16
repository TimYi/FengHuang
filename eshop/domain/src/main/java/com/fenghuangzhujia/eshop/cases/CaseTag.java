package com.fenghuangzhujia.eshop.cases;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 案例标签
 * 标签名称就是自身id，保证标签唯一
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_case_tags")
public class CaseTag {

	private String name;

	@Id
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
