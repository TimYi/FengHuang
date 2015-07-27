package com.fenghuangzhujia.eshop.view.decorateTechnology;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.view.decorateTechnology.dto.DecorateTechnologyDto;
import com.fenghuangzhujia.eshop.view.decorateTechnology.dto.DecorateTechnologyInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class DecorateTechnologyService extends
	DtoSpecificationService<DecorateTechnology, DecorateTechnologyDto, DecorateTechnologyInputArgs, String> {

	@Override
	public Iterable<DecorateTechnologyDto> findAll() {
		Sort sort=new Sort(new Sort.Order("ordernum"));
		Iterable<DecorateTechnology> result=getRepository().findAll(sort);
		return adapter.convertDoList(result);
	}
}
