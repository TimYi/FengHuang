package com.fenghuangzhujia.eshop.prudoct.packageContent.meteria;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.fenghuangzhujia.eshop.materialManage.material.Material;
import com.fenghuangzhujia.eshop.prudoct.packageContent.content.PackageContent;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_package_content_material")
public class PackageMaterial extends UUIDBaseModel {

	/**所属套餐内容*/
	private PackageContent content;
	/**排序序号*/
	private int ordernum;
	/**使用材料*/
	private Material material;
	
	@ManyToOne(optional=false)
	public PackageContent getContent() {
		return content;
	}
	public void setContent(PackageContent content) {
		this.content = content;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	@ManyToOne(optional=false)
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
}
