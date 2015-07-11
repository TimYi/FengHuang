package com.fenghuangzhujia.eshop.materialManage.material;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fhzj_material_type")
public class MaterialType {

	/**主材类别名称，如家具，窗饰*/
	private String name;
	/**排序序号*/
	private int ordernum;

	@Id
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	@Override
	public String toString() {
		if(name==null)return null;
		return name;
	}
}
