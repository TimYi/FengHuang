package com.fenghuangzhujia.eshop.prudoct.packageContent.content.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fenghuangzhujia.eshop.prudoct.packageContent.content.PackageContent;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

public class PackageContentAdapter extends AbstractDtoAdapter
	<PackageContent, PackageContentDto, PackageContentInputArgs> {
	
	@Autowired
	private DecoratePackageRepository decoratePackageRepository;

	@Override
	public PackageContentDto postConvert(PackageContent d, PackageContentDto t) {
		t.setPackageName(d.getDecoratePackage().getName());
		return t;
	}

	@Override
	public PackageContent postConvertToDo(PackageContentInputArgs i,
			PackageContent d) {
		return postUpdate(i, d);			
	}

	@Override
	public PackageContent postUpdate(PackageContentInputArgs i, PackageContent d) {
		String decoratePackageId=i.getDecoratePackageId();
		if(StringUtils.isNotBlank(decoratePackageId)) {
			DecoratePackage decoratePackage=decoratePackageRepository.findOne(decoratePackageId);
			d.setDecoratePackage(decoratePackage);
		}
		return d;
	}
}
