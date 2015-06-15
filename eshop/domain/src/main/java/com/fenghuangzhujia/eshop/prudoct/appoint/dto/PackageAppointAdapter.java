package com.fenghuangzhujia.eshop.prudoct.appoint.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppoint;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class PackageAppointAdapter extends AbstractDtoAdapter<PackageAppoint, PackageAppointDto, PackageAppointInputArgs> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private DecoratePackageRepository decoratePackageRepository;
	
	@Override
	public PackageAppointDto postConvert(PackageAppoint d, PackageAppointDto t) {
		return t;
	}

	@Override
	public PackageAppoint postConvertToDo(PackageAppointInputArgs i,
			PackageAppoint d) {
		return postUpdate(i, d);
	}

	@Override
	public PackageAppoint postUpdate(PackageAppointInputArgs i, PackageAppoint d) {
		String userid=i.getUserId();
		if(StringUtils.isNotBlank(userid)) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		String areaid=i.getCityId();
		if(StringUtils.isNotBlank(areaid)) {
			Area city=areaRepository.findOne(areaid);
			d.setCity(city);
		}
		String decoratePackageId=i.getDecoratePackageId();
		if(StringUtils.isNotBlank(decoratePackageId)) {
			DecoratePackage decoratePackage=decoratePackageRepository.findOne(decoratePackageId);
			d.setDecoratePackage(decoratePackage);
		}
		return d;
	}
}
