package com.fenghuangzhujia.eshop.prudoct.packageContent.content.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.prudoct.packageContent.material.dto.MaterialDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class PackageContentDto extends DtoBaseModel {

	/**名称，如家具，灯具*/
	private String name;
	/**套餐名称*/
	private String packageName;
	/**装修材料*/
	private Set<MaterialDto> materials;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Set<MaterialDto> getMaterials() {
		return materials;
	}
	public void setMaterials(Set<MaterialDto> materials) {
		this.materials = materials;
	}
}
