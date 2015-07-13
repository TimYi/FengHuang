package com.fenghuangzhujia.eshop.navigation.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.navigation.Navigation;
import com.fenghuangzhujia.eshop.navigation.NavigationRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class NavigationAdapter extends AbstractDtoAdapter<Navigation, NavigationDto, NavigationInputArgs> {

	@Autowired
	private NavigationRepository navigationRepository;
	
	@Override
	public NavigationDto postConvert(Navigation d, NavigationDto t) {
		return t;
	}

	@Override
	public Navigation postConvertToDo(NavigationInputArgs i, Navigation d) {
		return postUpdate(i, d);
	}

	@Override
	public Navigation postUpdate(NavigationInputArgs i, Navigation d) {
		String superNavigationId=i.getSuperNavigationId();
		if(StringUtils.isNotBlank(superNavigationId)) {
			Navigation superNavigation=navigationRepository.findOne(superNavigationId);
			d.setSuperNavigation(superNavigation);
		}
		return d;
	}
}
