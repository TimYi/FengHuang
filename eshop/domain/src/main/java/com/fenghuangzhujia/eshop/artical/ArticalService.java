package com.fenghuangzhujia.eshop.artical;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.artical.dto.ArticalDto;
import com.fenghuangzhujia.eshop.artical.dto.ArticalInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class ArticalService extends DtoSpecificationService<Artical, ArticalDto, ArticalInputArgs, String> {

	public ArticalDto findByCode(String code) {
		Artical artical=getRepository().findByCode(code);
		return adapter.convertToDetailedDto(artical);
	}
	
	@Override
	public ArticalRepository getRepository() {
		return (ArticalRepository)super.getRepository();
	}
}
