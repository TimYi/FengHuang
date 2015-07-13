package com.fenghuangzhujia.eshop.navigation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.navigation.dto.NavigationDto;
import com.fenghuangzhujia.eshop.navigation.dto.NavigationInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class NavigationService extends DtoPagingService<Navigation, NavigationDto, NavigationInputArgs, String> {

}
