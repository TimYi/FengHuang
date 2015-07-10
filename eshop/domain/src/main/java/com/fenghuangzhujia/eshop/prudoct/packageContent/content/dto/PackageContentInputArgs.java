package com.fenghuangzhujia.eshop.prudoct.packageContent.content.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class PackageContentInputArgs extends DtoBaseModel {

	/**所属套餐*/
	private String decoratePackageId;
	/**名称，如家具，灯具*/
	private String name;
	
	public String getDecoratePackageId() {
		return decoratePackageId;
	}
	public void setDecoratePackageId(String decoratePackageId) {
		this.decoratePackageId = decoratePackageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
