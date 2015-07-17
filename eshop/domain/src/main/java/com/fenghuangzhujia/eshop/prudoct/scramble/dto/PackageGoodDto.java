package com.fenghuangzhujia.eshop.prudoct.scramble.dto;

import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.commerce.goods.dto.GoodDto;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;

public class PackageGoodDto extends GoodDto {
	private DecoratePackageDto decoratePackage;
	private DecorateCaseDto decorateCase;
	
	public DecoratePackageDto getDecoratePackage() {
		return decoratePackage;
	}
	public void setDecoratePackage(DecoratePackageDto decoratePackage) {
		this.decoratePackage = decoratePackage;
	}
	public DecorateCaseDto getDecorateCase() {
		return decorateCase;
	}
	public void setDecorateCase(DecorateCaseDto decorateCase) {
		this.decorateCase = decorateCase;
	}
}
