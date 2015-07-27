package com.fenghuangzhujia.eshop.view.decorateTechnology;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.view.decorateTechnology.dto.DecorateTechnologyDto;
import com.fenghuangzhujia.eshop.view.decorateTechnology.dto.DecorateTechnologyInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class DecorateTechnologyService extends
	DtoSpecificationService<DecorateTechnology, DecorateTechnologyDto, DecorateTechnologyInputArgs, String> {

}
