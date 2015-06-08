package com.fenghuangzhujia.eshop.core.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.brand.dto.BrandTypeDto;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class BrandTypeService extends DtoPagingService<BrandType, BrandTypeDto, BrandTypeDto, String> {
	
	public PagedList<BrandTypeDto> findTopBrands(int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<BrandType> types=getRepository().findTopBrandTypes(pageable);
		Page<BrandTypeDto> result=types.map(adapter);
		return new PagedList<>(result);
	}
	
	@Autowired
	public void setBrandRepository(BrandTypeRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public BrandTypeRepository getRepository() {
		return (BrandTypeRepository)super.getRepository();
	}
}
