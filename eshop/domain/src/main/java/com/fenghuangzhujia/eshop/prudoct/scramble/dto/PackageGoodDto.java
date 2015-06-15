package com.fenghuangzhujia.eshop.prudoct.scramble.dto;

import com.fenghuangzhujia.eshop.core.commerce.goods.dto.GoodDto;
import com.fenghuangzhujia.eshop.prudoct.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;

public class PackageGoodDto extends GoodDto {
	private DecoratePackageDto decoratingPackage;
	private DecorateCaseDto decorateCase;
	
	public DecoratePackageDto getDecoratingPackage() {
		return decoratingPackage;
	}
	public void setDecoratingPackage(DecoratePackageDto decoratingPackage) {
		this.decoratingPackage = decoratingPackage;
	}
	public DecorateCaseDto getDecorateCase() {
		return decorateCase;
	}
	public void setDecorateCase(DecorateCaseDto decorateCase) {
		this.decorateCase = decorateCase;
	}
}
