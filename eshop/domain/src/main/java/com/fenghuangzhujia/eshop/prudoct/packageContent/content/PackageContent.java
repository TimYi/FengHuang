package com.fenghuangzhujia.eshop.prudoct.packageContent.content;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.prudoct.packageContent.meteria.PackageMaterial;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_package_content")
public class PackageContent extends UUIDBaseModel {

	/**所属套餐*/
	private DecoratePackage decoratePackage;
	/**名称，如家具，灯具*/
	private String name;
	/**装修材料*/
	private Set<PackageMaterial> materials;
	
	@ManyToOne(optional=false)
	public DecoratePackage getDecoratePackage() {
		return decoratePackage;
	}
	public void setDecoratePackage(DecoratePackage decoratePackage) {
		this.decoratePackage = decoratePackage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="content")
	public Set<PackageMaterial> getMaterials() {
		return materials;
	}
	public void setMaterials(Set<PackageMaterial> materials) {
		this.materials = materials;
	}
}
